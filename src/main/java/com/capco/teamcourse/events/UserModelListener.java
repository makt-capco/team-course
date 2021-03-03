package com.capco.teamcourse.events;

import com.capco.teamcourse.db.model.User;
import com.capco.teamcourse.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class UserModelListener extends AbstractMongoEventListener<User> {

    private final SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public UserModelListener(SequenceGeneratorService sequenceGeneratorService) {

        this.sequenceGeneratorService = sequenceGeneratorService;

    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<User> event) {

        if (event.getSource().getId() == null) {
            event.getSource().setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
        }
    }

}
