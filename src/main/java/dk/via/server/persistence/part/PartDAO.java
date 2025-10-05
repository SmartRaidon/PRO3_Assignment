package dk.via.server.persistence.part;

import dk.via.domain.Part;

import java.util.List;

public interface PartDAO {
    void create(Part part);
    Part getPartById(int id);
    void delete(int id);
    Part update (Part part);
    List<Part> getAllParts();
}
