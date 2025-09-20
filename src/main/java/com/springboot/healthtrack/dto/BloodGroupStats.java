package com.springboot.healthtrack.dto;

import com.springboot.healthtrack.entity.type.BloodGroupType;
import lombok.Data;

@Data
public class BloodGroupStats {
    private final BloodGroupType bloodGroupType;
    private final Long count;
}
