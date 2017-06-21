/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.List;
import models.Link;

/**
 *
 * @author Isuru
 */
public interface Linkable {
    public List<Link> getLinks();
    public void setLinks(List<Link> _links);
}
