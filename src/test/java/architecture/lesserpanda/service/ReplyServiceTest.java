package architecture.lesserpanda.service;

import architecture.lesserpanda.entity.Reply;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class ReplyServiceTest {

    @Autowired ReplyService replyService;

    @Test
    public void createReply(){
        Reply reply1 = new Reply("test");
        Long id = replyService.saveReply(reply1);

        Reply findReply = replyService.findReply(id);

        assertThat(findReply).isEqualTo(reply1);
    }

    @Test
    public void findAll(){
        Reply reply1 = new Reply("reply1");
        Reply reply2 = new Reply("reply2");

        replyService.saveReply(reply1);
        replyService.saveReply(reply2);

        List<Reply> all = replyService.findAll();
        assertThat(all.size()).isEqualTo(2);
    }

    @Test
    @Transactional
    public void deleteReply(){
        Reply reply1 = new Reply("reply1");
        Reply reply2 = new Reply("reply2");

        Long id1 = replyService.saveReply(reply1);
        Long id2 = replyService.saveReply(reply2);

        replyService.deleteReply(id1);

        List<Reply> all = replyService.findAll();
        assertThat(all.size()).isEqualTo(1);
    }
}