/* * MIT License * * Copyright (c) 2020 Ursin Filli * * Permission is hereby granted, free of charge, to any person obtaining a copy * of this software and associated documentation files (the "Software"), to deal * in the Software without restriction, including without limitation the rights * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell * copies of the Software, and to permit persons to whom the Software is * furnished to do so, subject to the following conditions: * * The above copyright notice and this permission notice shall be included in all * copies or substantial portions of the Software. * * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE * SOFTWARE. * */package dev.ursinn.schule.m226.zorkgame;import java.io.BufferedReader;import java.io.InputStreamReader;import java.util.StringTokenizer;public class Parser {    private CommandWords commands; // holds all valid command words    public Parser() {        commands = new CommandWords();    }    public Command getCommand() {        String inputLine = ""; // will hold the full input line        String word1;        String word2;        System.out.print("> "); // print prompt        BufferedReader reader = new BufferedReader(new InputStreamReader(                System.in));        try {            inputLine = reader.readLine();        } catch (java.io.IOException exc) {            System.out.println("There was an error during reading: "                    + exc.getMessage());        }        StringTokenizer tokenizer = new StringTokenizer(inputLine);        if (tokenizer.hasMoreTokens()) {            word1 = tokenizer.nextToken(); // get first word        } else {            word1 = null;        }        if (tokenizer.hasMoreTokens()) {            word2 = tokenizer.nextToken(); // get second word        } else {            word2 = null;        }        // note: we just ignore the rest of the input line.        // Now check whether this word is known. If so, create a command        // with it. If not, create a "nil" command (for unknown command).        if (commands.isCommand(word1)) {            return new Command(word1, word2);        } else {            return new Command(null, word2);        }    }    /**     * Print out a list of valid command words.     */    public String showCommands() {        return commands.showAll();    }}