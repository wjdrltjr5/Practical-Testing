package sample.cafekiosk.spring.client.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MailSendClient {

    public boolean sendMail(String fromEmail, String toEmail, String subject, String content) {
        log.info("메일 전송");
        throw new IllegalArgumentException("메일 전송");
    }
}
