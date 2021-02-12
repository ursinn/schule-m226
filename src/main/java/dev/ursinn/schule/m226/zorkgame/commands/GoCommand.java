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
import dev.ursinn.schule.m226.zorkgame.items.*;
import dev.ursinn.schule.m226.zorkgame.rooms.*;

import java.util.concurrent.atomic.AtomicBoolean;

public class GoCommand implements CommandInterface {

    @Override
    public void command(String cmd, String[] args) {
        if (args.length == 0)
            System.out.println("Go where?");
        else {
            String direction = args[0];

            // Try to leave current room.
            Room nextRoom = Game.getInstance().currentRoom.nextRoom(direction);

            if (nextRoom == null)
                System.out.println("There is no door!");
            else {
                if (nextRoom.isLocked()) {
                    if (nextRoom.shortDescription().equalsIgnoreCase(new Reception().shortDescription()))
                        unlock(unlockRoom(new ReceptionKey()), nextRoom);
                    else if (nextRoom.shortDescription().equalsIgnoreCase(new BuildingB().shortDescription()))
                        unlock(unlockRoom(new BuildingBKey()), nextRoom);
                    else if (nextRoom.shortDescription().equalsIgnoreCase(new Office().shortDescription()))
                        unlock(unlockRoom(new OfficeKey()), nextRoom);
                    else if (nextRoom.shortDescription().equalsIgnoreCase(new Roof().shortDescription()))
                        unlock(unlockRoom(new RoofKey()), nextRoom);
                    else if (nextRoom.shortDescription().equalsIgnoreCase(new Lab().shortDescription()))
                        unlock(unlockRoom(new LabNFCCard()), nextRoom);
                    else if (nextRoom.shortDescription().equalsIgnoreCase(new SecretOffice().shortDescription()))
                        unlock(unlockRoom(new SecretOfficeCode()) && unlockRoom(new SecretOfficeKey()), nextRoom);
                    else if (nextRoom.shortDescription().equalsIgnoreCase(new BuildingA().shortDescription()))
                        unlock(unlockRoom(new BuildingAKey()), nextRoom);
                    else if (nextRoom.shortDescription().equalsIgnoreCase(new TestRoom().shortDescription()))
                        unlock(unlockRoom(new TestRoomKey()), nextRoom);
                    else
                        System.err.println("Door Locked (Unknown Door)");
                } else
                    goNextRoom(nextRoom);
            }
        }
    }

    private void goNextRoom(Room nextRoom) {
        Game.getInstance().lastRoom = Game.getInstance().currentRoom;
        Game.getInstance().currentRoom = nextRoom;
        System.out.println(nextRoom.longDescription());
    }

    private boolean unlockRoom(Item unlock) {
        AtomicBoolean back = new AtomicBoolean(false);
        Game.getInstance().inventory.getItems().forEach(item -> {
            if (item.getType() == unlock.getType()) {
                if (item.getName().equalsIgnoreCase(unlock.getName()))
                    back.set(true);
            }
        });
        return back.get();
    }

    private void unlock(boolean b, Room nextRoom) {
        if (b) {
            nextRoom.setLocked(false);
            goNextRoom(nextRoom);
        } else
            System.err.println("Door Locked");
    }
}
