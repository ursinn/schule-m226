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
import dev.ursinn.schule.m226.zorkgame.items.BuildingAKey;
import dev.ursinn.schule.m226.zorkgame.items.TestRoomKey;

import java.util.ArrayList;

public class SecretOffice extends Room {

    public SecretOffice() {
        super("Secret Office", true);
        ArrayList<Question> questions = new ArrayList<>();
        ArrayList<Question> questions2 = new ArrayList<>();
        ArrayList<Question> questions3 = new ArrayList<>();
        ArrayList<Person> persons = new ArrayList<>();
        questions.add(new Question("Key?", "Da", new TestRoomKey()));
        questions2.add(new Question("key?", "Da", new BuildingAKey()));
        questions3.add(new Question("Hallo?", "Hallo!", null));
        persons.add(new Person(questions, "Peter"));
        persons.add(new Person(questions2, "Alex"));
        persons.add(new Person(questions3, "Tobi"));
        persons.add(new Person(null, "Marvin"));
        setPersons(persons);
    }
}
