/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.ws.rs.core.Response;
import javax.xml.registry.InvalidRequestException;
import models.RegisterRequest;

/**
 *
 * @author Isuru
 */
public interface RegistrationManagementServiceRemote {
    Response registerUser(RegisterRequest request) throws Exception;
}
