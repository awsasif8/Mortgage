package com.ing.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatementResponseDto {
private String message;
private String status;
private int statusCode;
List<StatementDto> data;
}
