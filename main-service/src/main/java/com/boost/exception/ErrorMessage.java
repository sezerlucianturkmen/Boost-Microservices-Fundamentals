package com.boost.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Component
public class ErrorMessage {
    private int code;
    private String message;
    private List<String> fields;

}