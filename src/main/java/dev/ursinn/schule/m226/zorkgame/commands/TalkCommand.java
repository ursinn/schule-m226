/*
 * MIT License
 *
 * Copyright (c) 2020-2022 Ursin Filli
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.ursinn.schule.m226.zorkgame.commands;

import dev.ursinn.schule.m226.zorkgame.Game;
import dev.ursinn.schule.m226.zorkgame.rooms.Reception;
import dev.ursinn.schule.m226.zorkgame.rooms.Room;

public class TalkCommand implements CommandInterface {

    @Override
    public void command(String cmd, String[] args) {
        if (args.length > 2)
            talk(args[0], args[1]);
        else if (args.length != 0)
            talk(args[0], null);
        else
            System.err.println("Error: talk <Name>");
    }

    private void talk(String name, String ask) {
        Room currentRoom = Game.getInstance().currentRoom;
        if (!currentRoom.isSearched()) {
            System.out.println("Du must den Raum erst durchsuchen mit search");
            return;
        }
        if (!currentRoom.hasPersons()) {
            System.out.println("No Persons in Room");
            return;
        }
        if (ask == null)
            currentRoom.getPersons().forEach(person -> {
                if (person.hasQuestions()) {
                    if (person.name().equalsIgnoreCase(name)) {
                        person.questions().forEach(question -> System.out.println(question.question()));
                        if (currentRoom.shortDescription().equalsIgnoreCase(new Reception().shortDescription())) {
                            if (Game.getInstance().foundSecretRoom) {
                                System.out.println("xxx");
                            }
                        }
                    }
                }
            });
        else {
            currentRoom.getPersons().forEach(person -> {
                if (person.hasQuestions()) {
                    if (person.name().equalsIgnoreCase(name)) {
                        if (ask.equalsIgnoreCase("xxx")) { // TODO
                            if (currentRoom.shortDescription().equalsIgnoreCase(new Reception().shortDescription())) {
                                if (Game.getInstance().foundSecretRoom) {
                                    System.out.println("Da");
                                    Game.getInstance().currentRoom.setExits(Game.getInstance().buildingC, Game.getInstance().secretOffice, null, null);
                                    Game.getInstance().reception.setExits(Game.getInstance().buildingC, Game.getInstance().secretOffice, null, null);
                                }
                            }
                        }
                        person.questions().forEach(question -> {
                            if (question.question().equalsIgnoreCase(ask)) {
                                System.out.println(question.answer());
                                if (question.hasItem())
                                    Game.getInstance().inventory.addItem(question.item());
                            }
                        });
                    }
                }
            });
        }
    }
}
