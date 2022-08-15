package ru.netology;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Meeting {
    private final String city;
    private final String futuredateFirstMeeting;
    private final String name;
    private final String phoneNumber;
    private final String futuredateSecondMeeting;

    }
