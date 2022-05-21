package com.printers;

import com.loaders.InstructorLoader;
import com.models.Instructor;
import de.vandermeer.asciitable.AsciiTable;

import java.util.ArrayList;

public class InstructorPrinter {
    private static final InstructorLoader instructorLoader = new InstructorLoader();

    public static void printInstructors(ArrayList<Instructor> instructors) {
        if (instructors.size() == 0) {
            System.out.println("Oh looks like there is no instructors.");
            return;
        }

        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("Id", "Name");
        table.addRule();

        for (Instructor i : instructors) {
            Instructor instructorUser = instructorLoader.find(i.getId());

            table.addRow(instructorUser.getId(), String.format("%s %s", instructorUser.getForename(), instructorUser.getSurname()));
            table.addRule();
        }

        System.out.println(table.render());
    }
}
