package dk.via.server.service.tray;

import dk.via.domain.Tray;
import dk.via.server.persistence.tray.TrayDAO;

import java.util.List;

public class TrayServiceImpl implements TrayService {

    private final TrayDAO trayDAO;

    public TrayServiceImpl(TrayDAO trayDAO) {
        this.trayDAO = trayDAO;
    }

    @Override
    public Tray createTray(String partType, double maxWeight) {
        Tray tray = new Tray();
        tray.setPartType(partType);
        tray.setMaxWeight(maxWeight);
        return trayDAO.create(tray);
    }

    @Override
    public Tray getTray(int id) {
        return trayDAO.getTrayById(id);
    }

    @Override
    public List<Tray> getAllTrays() {
        return trayDAO.getAll();
    }

    @Override
    public void delete(int id) {
        trayDAO.delete(id);
    }

    @Override
    public Tray update(Tray tray) {
        return  trayDAO.update(tray);
    }
}
