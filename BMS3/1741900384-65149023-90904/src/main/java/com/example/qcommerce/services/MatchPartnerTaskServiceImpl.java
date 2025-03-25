package com.example.qcommerce.services;

import com.example.qcommerce.models.Partner;
import com.example.qcommerce.models.PartnerTaskMapping;
import com.example.qcommerce.models.Task;
import com.example.qcommerce.repositories.PartnerRepository;
import com.example.qcommerce.repositories.PartnerTaskRepository;
import com.example.qcommerce.repositories.TaskRepository;
import com.example.qcommerce.strategies.PartnerAssignmentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchPartnerTaskServiceImpl implements MatchPartnerTaskService {
    PartnerRepository partnerRepository;
    TaskRepository taskRepository;
    PartnerTaskRepository partnerTaskRepository;
    PartnerAssignmentStrategy partnerAssignmentStrategy;
    @Autowired
    public MatchPartnerTaskServiceImpl(PartnerRepository partnerRepository, TaskRepository taskRepository, PartnerTaskRepository partnerTaskRepository, PartnerAssignmentStrategy partnerAssignmentStrategy) {
        this.partnerRepository = partnerRepository;
        this.taskRepository = taskRepository;
        this.partnerTaskRepository = partnerTaskRepository;
        this.partnerAssignmentStrategy = partnerAssignmentStrategy;
    }

    @Override
    public List<PartnerTaskMapping> matchPartnersAndTasks(List<Long> partnerIds, List<Long> taskIds) throws Exception {
        List<Partner> partnerList = new ArrayList<>();
        List<Task> taskList = new ArrayList<>();
        for(Long partnerId : partnerIds){
            Partner partner = partnerRepository.findById(partnerId).orElseThrow(()-> new Exception("Could not find partner"));
            partnerList.add(partner);
        }
        for(Long taskId : taskIds){
            Task task = taskRepository.findById(taskId).orElseThrow(()-> new Exception("Could not find task"));
            taskList.add(task);
        }
        List<PartnerTaskMapping> partnerTaskMappings = partnerAssignmentStrategy.getPartnerTasks(partnerList, taskList);
        return partnerTaskRepository.saveAll(partnerTaskMappings);
    }
}
