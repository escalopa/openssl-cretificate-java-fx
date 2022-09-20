package source.opensslcretificatejavafx.services;

import source.opensslcretificatejavafx.configuration.annotations.Autowire;
import source.opensslcretificatejavafx.configuration.annotations.Component;

import java.io.*;

@Component
public class ExecuteCommand {

    /***
     * For running commands on OS we use different syntax
     * 1. Linux -> bash, -c
     * 2/ Windows -> cmd.exe, /c
     */

    @Autowire
    public Settings settings;

    //Create PK
    public void createPrivateKey(String keyName) throws IOException {
        execute(settings.getPrivateKeyCommand(keyName));
    }

    //Create Organization certificate
    public void createOrganizationCertificate() throws IOException {
        execute(settings.getOrganizationCertificateCommand());
    }

    //Create User certificate
    public void createUserOrganizationCertificate() throws IOException {
        execute(settings.getUserCertificateRequestCommand());
        execute(settings.getUserCertificateCommand());
    }

    private void execute(String command) throws IOException {
        System.out.println(command + " -> " + settings.directory) ;
        ProcessBuilder processBuilder = new ProcessBuilder().command("bash", "-c", command)
                .directory(new File(settings.directory));
        Process process = processBuilder.start();
        printCommandResults(process.getInputStream());
    }

    //Print value of executed command
    private void printCommandResults(InputStream inputStream) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));

        String s = null;
        while (true) {
            try {
                if ((s = bf.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(s);
        }
    }
}
