package source.opensslcretificatejavafx.utils;

public class DefaultCommands {

    //PK
    //openssl genrsa -out rootCA.key 2048
    public static final String GenPKCommand = " openssl gen%s -out %s%s.key %d ";

    //Organization command(s)
    //openssl req -x509 -utf8 -new -key rootCA.key -days 10000 -out rootCA.crt
    public static final String OrganizationCertificateCommand = " openssl req -x509 -utf8 -new -key " +
            " %s%s.key -days 365 -out %s%s.crt %s";

    //User command
    //openssl req -new -utf8 -key user.key -out user.csr
    public static final String UserCertificateRequestCommand = " openssl req -new -utf8 -key %s%s.key -out %s%s.csr %s ";

    //openssl x509 -req -in user.csr -CA rootCA.crt -CAkey rootCA.key -CAcreateserial -out user.crt -days 365
    public static final String UserCertificateCommand = " openssl x509 -req -in %s%s.csr " +
            "-CA %s -CAkey %s -CAcreateserial -out %s%s.crt -days 365 ";

    //Certificate data command
    //static final String SUBJCommand = " -subj /C=%s/ST=%s/L=%s/O=%s/OU=%s/CN=%s/emailAddress=%s/ ";
}
