package org.revo;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.IOException;

/**
 * @goal Test Mojo
 */
public class TestMojo extends AbstractMojo {
    /**
     * @parameter
     */
    private String from;
    /**
     * @parameter
     */
    private String to;

    public void execute()
            throws MojoExecutionException {
        if (from != null && to != null) {
            getLog().info(from + "        " + to);
            String go = "cd ";
            String back = "-";
            String build = "ng build";
            String copy = "cp -R ";
            String remove = "rm -rf ";
            String dist = from + "/dist";
            String space = " ";
            String argument = remove + dist + ";" + go + from + ";" + build + ";" + go + back + ";" + remove + to + ";" + copy + dist + space + to;
            getLog().info(argument.replace(";", "\n"));
            try {
                new DefaultExecutor().execute(new CommandLine("/bin/sh").addArgument("-c").addArgument(argument, false));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
