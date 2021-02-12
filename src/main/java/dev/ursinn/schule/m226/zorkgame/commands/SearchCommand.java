/*
 * MIT License
 *
 * Copyright (c) 2020 - 2021 Ursin Filli
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
 *
 */

package dev.ursinn.schule.m226.zorkgame.commands;

import dev.ursinn.schule.m226.zorkgame.Game;
import dev.ursinn.schule.m226.zorkgame.items.ItemType;
import dev.ursinn.schule.m226.zorkgame.rooms.Office;
import dev.ursinn.schule.m226.zorkgame.rooms.Room;

public class SearchCommand implements CommandInterface {

    @Override
    public void command(String cmd, String[] args) {
        Room currentRoom = Game.getInstance().currentRoom;
        if (currentRoom.isSearched())
            System.out.println("Der Raum wurde bereits durchsucht!");
        currentRoom.setSearched(true);
        if (!currentRoom.hasItems() && !currentRoom.hasPersons()) {
            System.err.println("No Items and No Persons");
            return;
        }
        if (currentRoom.hasItems())
            currentRoom.getItems().forEach(item -> {
                if (item.getType() == ItemType.OBJECT) {
                    System.out.println("Durchsuche: " + item.getName() + "...");
                    if (item.isContainingItem()) {
                        System.out.println("Gefunden: " + item.getContains().getName());
                    } else
                        System.out.println("Nichts gefunden!");
                }
            });
        if (currentRoom.hasPersons())
            currentRoom.getPersons().forEach(person -> System.out.println("Sehe Person: " + person.getName()));
        if (currentRoom.shortDescription().equalsIgnoreCase(new Office().shortDescription())) {
            Game.getInstance().currentRoom.setExits(Game.getInstance().buildingB, Game.getInstance().roof, null, null);
            Game.getInstance().office.setExits(Game.getInstance().buildingB, Game.getInstance().roof, null, null);
        }
    }
}
