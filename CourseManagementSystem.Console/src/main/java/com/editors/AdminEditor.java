package com.editors;

import com.loaders.AdminLoader;
import com.models.Admin;
import com.savers.AdminSaver;

import java.util.ArrayList;
import java.util.UUID;

public class AdminEditor implements IEditor<Admin> {
    private final AdminLoader loader = new AdminLoader();
    private final AdminSaver saver = new AdminSaver();

    public void add(Admin entity) {
        ArrayList<Admin> admins = this.loader.loadAll();
        admins.add(entity);
        this.saver.saveAll(admins);
    }

    public void update(Admin entity) {
        int index = 0;
        ArrayList<Admin> admins = this.loader.loadAll();
        UUID adminId = entity.getId();

        for (int i = 0; i < admins.size(); i++) {
            if (admins.get(i).getId().equals(adminId)) {
                index = i;
                break;
            }
        }

        admins.set(index, entity);
        this.saver.saveAll(admins);
    }

    public void delete(Admin entity) {
        ArrayList<Admin> admins = this.loader.loadAll();
        admins.remove(entity);
        this.saver.saveAll(admins);
    }

    public void delete(UUID id) {
        int index = 0;
        ArrayList<Admin> admins = this.loader.loadAll();

        for (int i = 0; i < admins.size(); i++) {
            if (admins.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }

        admins.remove(index);
        this.saver.saveAll(admins);
    }
}
