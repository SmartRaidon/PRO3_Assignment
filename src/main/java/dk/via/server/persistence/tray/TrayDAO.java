package dk.via.server.persistence.tray;

import dk.via.domain.Tray;

import java.util.List;

public interface TrayDAO {
    Tray create(Tray tray);
    Tray getTrayById(int id);
    void delete(int id);
    Tray update (Tray tray);
    List<Tray> getAll();
}
