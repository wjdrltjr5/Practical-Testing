package sample.cafekiosk.spring.api.service.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk.spring.client.mail.MailSendClient;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;

@Service
@RequiredArgsConstructor
public class MailService {

    private final MailSendClient mailSendClient;
    private final MailSendHistoryRepository mailSendHistoryRepository;
    public boolean sendMail(String fromEmail, String toEmail, String subject, String content) {
        boolean result = mailSendClient.sendMail(fromEmail,toEmail,subject,content);

        if(result){
            mailSendHistoryRepository.save(MailSendHistory.builder()
                            .fromEmail(fromEmail)
                            .subject(subject)
                            .toEmail(toEmail)
                            .content(content)
                    .build());
            return  true;
        }
        return false;
    }

}
