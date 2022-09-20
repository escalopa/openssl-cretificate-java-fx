package source.opensslcretificatejavafx.utils;

public class CertificateData {

    public String countryCode = "";
    public String city = "";
    public String region = "";
    public String organization = "";
    public String department = "";
    public String username = "";
    public String email = "";

    public String getDataInCommandFormat() {
        return String.format(" -subj /C=%s/ST=%s/L=%s/O=%s/OU=%s/CN=%s/emailAddress=%s/",
                countryCode,
                region,
                city,
                organization,
                department,
                username,
                email);
    }
}
