package com.aldwindelgado.github.springawssnspublisher.dto;

import java.io.Serializable;
import java.util.Map;
import lombok.Data;

/**
 * @author Aldwin Delgado on Mar 14, 2020
 */
@Data
public class SNSPublisherDTO implements Serializable {

    private static final long serialVersionUID = 3530284196279993488L;

    private String subject;

    private Map<String, Object> message;

}
