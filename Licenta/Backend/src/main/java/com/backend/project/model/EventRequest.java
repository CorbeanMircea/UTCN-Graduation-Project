package com.backend.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventRequest {
    String eventCode, name, location, program, address, date, description;
    int spotsAvailable;
}
