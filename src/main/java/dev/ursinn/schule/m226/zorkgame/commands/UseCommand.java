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
import dev.ursinn.schule.m226.zorkgame.items.Binoculars;
import dev.ursinn.schule.m226.zorkgame.items.ItemType;
import dev.ursinn.schule.m226.zorkgame.rooms.Office;

public class UseCommand implements CommandInterface {

    @Override
    public void command(String cmd, String[] args) {
        if (Game.getInstance().inventory.getItems().isEmpty())
            return;
        if (Game.getInstance().currentRoom.shortDescription().equalsIgnoreCase(new Office().shortDescription())) {
            Game.getInstance().inventory.getItems().forEach(item -> {
                if (item.getType() == ItemType.ITEM) {
                    if (item.getName().equalsIgnoreCase(new Binoculars().getName())) {
                        Game.getInstance().reception.setExits(Game.getInstance().buildingC, Game.getInstance().secretOffice, null, null);
                        System.out.println("Secret Office Found!");
                        Game.getInstance().foundSecretRoom = true;
                    }
                }
            });
        }
    }
}
