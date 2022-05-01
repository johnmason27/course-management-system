package com.models;

import com.consts.FileNames;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.io.IOManager;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

public class Modules {
    private static IOManager ioManager;
    private static ArrayList<Module> modules;

    public Modules() {
        ioManager = new IOManager();
        String modulesFileContent = ioManager.readFile(FileNames.Modules);
        Gson gson = new Gson();
        Type moduleListType = new TypeToken<ArrayList<Module>>(){}.getType();
        modules = gson.fromJson(modulesFileContent, moduleListType);
    }

    public static ArrayList<Module> getModules() {
        return modules;
    }

    public static void addModule(Module module) {
        modules.add(module);
        saveModules();
    }

    public static void addModules(ArrayList<Module> newModules) {
        modules.addAll(newModules);
        saveModules();
    }

    public static void removeModule(Module module) {
        modules.remove(module);
        saveModules();
    }

    public static void removeModules(ArrayList<Module> modulesToDelete) {
        modules.removeAll(modulesToDelete);
        saveModules();
    }

    public static Module findModule(String moduleName) {
        return modules.stream()
                .filter(m -> moduleName.equals(m.getModuleName()))
                .findAny()
                .orElse(null);
    }

    public static void updateModule(Module module) {
        int index = 0;
        String moduleName = module.getModuleName();

        for (int i = 0; i < modules.size(); i ++) {
            if (Objects.equals(modules.get(i).getModuleName(), moduleName)) {
                index = i;
                break;
            }
        }

        modules.set(index, module);
        saveModules();
    }

    private static void saveModules() {
        Gson gson = new Gson();
        String modulesJson = gson.toJson(Modules.modules);
        Modules.ioManager.writeFile(FileNames.Modules, modulesJson);
    }
}
