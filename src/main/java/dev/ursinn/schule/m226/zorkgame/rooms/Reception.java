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

package dev.ursinn.schule.m226.zorkgame.rooms;

import dev.ursinn.schule.m226.zorkgame.Person;
import dev.ursinn.schule.m226.zorkgame.Question;
import dev.ursinn.schule.m226.zorkgame.items.BuildingBKey;

import java.util.ArrayList;
import java.util.List;

public class Reception extends Room {

    public Reception() {
        super("Reception", true);
        List<Question> questions = new ArrayList<>();
        List<Person> persons = new ArrayList<>();
        questions.add(new Question("Hallo?", "Hallo", null));
        questions.add(new Question("Key?", "Da", new BuildingBKey()));
        persons.add(new Person(questions, "Hans"));
        setPersons(persons);
    }
}
