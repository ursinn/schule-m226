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

package dev.ursinn.schule.m226.zorkgame;

import dev.ursinn.schule.m226.zorkgame.items.Item;
import dev.ursinn.schule.m226.zorkgame.items.ItemType;

import java.util.ArrayList;

public class Inventory {

    private int maxWight;
    private ArrayList<Item> items;
    private int currentWight;

    public Inventory(int maxWight) {
        this.maxWight = maxWight;
        this.currentWight = 0;
        this.items = new ArrayList<>();
    }

    public boolean addItem(Item item) {
        if (items.contains(item)) return false;
        if (item.getType() == ItemType.ITEM || item.getType() == ItemType.CODE || item.getType() == ItemType.KEY) {
            if (item.getWight() == 0) {
                items.add(item);
                return true;
            } else if (item.getWight() + currentWight <= maxWight) {
                items.add(item);
                currentWight += item.getWight();
                return true;
            }
        }
        return false;
    }

    public boolean removeItem(Item item) {
        if (item.getType() == ItemType.KEY) {
            items.remove(item);
            currentWight -= item.getWight();
            return true;
        }
        return false;
    }

    public int getMaxWight() {
        return maxWight;
    }

    public void setMaxWight(int maxWight) {
        this.maxWight = maxWight;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public int getCurrentWight() {
        return currentWight;
    }
}
