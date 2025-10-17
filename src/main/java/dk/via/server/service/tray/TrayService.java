package dk.via.server.service.tray;

import dk.via.domain.Tray;

import java.util.List;

public interface TrayService {
    Tray createTray(String partType, double maxWeight);
    Tray getTray(int id);
    List<Tray> getAllTrays();
    void delete(int id);
    Tray update(Tray tray);
}
