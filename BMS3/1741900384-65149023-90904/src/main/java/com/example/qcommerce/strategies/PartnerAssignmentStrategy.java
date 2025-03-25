package com.example.qcommerce.strategies;

import com.example.qcommerce.models.Partner;
import com.example.qcommerce.models.PartnerTaskMapping;
import com.example.qcommerce.models.Task;

import java.util.List;

public interface PartnerAssignmentStrategy {
    List<PartnerTaskMapping> getPartnerTasks(List<Partner> partners, List<Task> tasks);
}
