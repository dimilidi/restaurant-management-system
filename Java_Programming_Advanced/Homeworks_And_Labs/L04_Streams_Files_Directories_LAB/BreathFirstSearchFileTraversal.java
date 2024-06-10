package Homeworks_And_Labs.L04_Streams_Files_Directories_LAB;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayDeque;
import java.util.Deque;

public class BreathFirstSearchFileTraversal {
    // Breadth-First Search (BFS) systematically traverses the graph by exploring each level or layer before moving on to the next,
    // -> implemented with Queue ( loop while queue !isEmpty -> print files/ add directories to queue
    // Depth-First Search (DFS)prioritizes delving as deeply as possible along a single branch or path before backtracking to explore alternative paths.
    // -> implemented with Recursion
    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        String path = dir + "/Java_Programming_Advanced/Homeworks_And_Labs/Streams/04. Java-Advanced-Files-and-Streams-Lab-Resources";

        Deque<File> queue = new ArrayDeque<>();

        queue.offer(new File(path));

        int counter = 0;
        System.out.println(new File(path).exists());

        while(!queue.isEmpty()) {
            File f = queue.poll();
            counter++;

            File[] files = f.listFiles();

            if(files != null){
                for (File file : files) {
                    queue.offer(file);
                }
            }
        }
        System.out.println(counter);

    }
}
