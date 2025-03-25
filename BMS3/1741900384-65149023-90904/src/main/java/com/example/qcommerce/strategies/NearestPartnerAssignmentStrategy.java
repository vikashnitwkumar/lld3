package com.example.qcommerce.strategies;

import com.example.qcommerce.models.Partner;
import com.example.qcommerce.models.PartnerTaskMapping;
import com.example.qcommerce.models.Task;
import com.example.qcommerce.utils.DistanceUtils;
import lombok.Getter;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class NearestPartnerAssignmentStrategy implements PartnerAssignmentStrategy{
   @Getter
    class PPair{
        public PartnerTaskMapping partnerTaskMapping;
        public Double distance;
        public PPair(PartnerTaskMapping partnerTaskMapping, Double distance){
            this.partnerTaskMapping = partnerTaskMapping;
            this.distance = distance;
        }
    }
    @Override
    public List<PartnerTaskMapping> getPartnerTasks(List<Partner> partners, List<Task> tasks) {
        int totalPartners = partners.size();
        int totalTasks = tasks.size();
        if(totalPartners == 0 || totalTasks == 0) return null;
        int maxAllowedTask = totalTasks / totalPartners;
        int remainingAdditionalTasks =  totalTasks % totalPartners;
        Map<Partner, Integer> partnerTasksCount = new HashMap<>();
        Queue<PPair> taskQueue = new PriorityQueue<>(
               (PPair a, PPair b) -> Double.compare(a.distance , b.distance) )  ;
        for(Partner p : partners){
            for(Task task: tasks){
                PartnerTaskMapping partnerTaskMapping = new PartnerTaskMapping();
                partnerTaskMapping.setPartner(p);
                partnerTaskMapping.setTask(task);
                Double dis = DistanceUtils.calculateDistance(p.getCurrentLocation(), task.getPickupLocation());
                taskQueue.offer(new PPair(partnerTaskMapping, dis));
            }
        }
        Map<Task, Boolean> takenTask = new HashMap<>();
        List<PartnerTaskMapping> result = new ArrayList<>();
        while (!taskQueue.isEmpty()){
            PPair pPair = taskQueue.poll();
            Task task = pPair.partnerTaskMapping.getTask();
            Partner partner = pPair.partnerTaskMapping.getPartner();
            if(takenTask.containsKey(task)) continue;

            int count = partnerTasksCount.getOrDefault(partner, 0);
            if(count < maxAllowedTask  ){
                partnerTasksCount.put(partner, count+1);
                result.add(pPair.getPartnerTaskMapping());
                takenTask.put(task, true);
            }
            else if(count == maxAllowedTask && remainingAdditionalTasks >0 ){
                partnerTasksCount.put(partner, count+1);
                remainingAdditionalTasks--;
                result.add(pPair.getPartnerTaskMapping());
                takenTask.put(task, true);
            }
        }
        return result;
    }
}
