package com.email_writer.Data;


import lombok.Data;

@Data
public class EmailRequest {
    private String emailContent;
    private String tone;
}
