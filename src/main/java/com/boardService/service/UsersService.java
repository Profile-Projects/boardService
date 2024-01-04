package com.boardService.service;

import com.boardService.models.Board;
import com.boardService.models.Users;
import com.boardService.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService extends BaseService<UsersRepository, Users, String> {

    @Autowired
    private UsersRepository usersRepository;

    protected Integer nextSid;

    protected String prefix = "US";

    @Override
    public Users add(final Users users) {
        findNextSid();
        final Users newUser = Users.from(users);
        final String sid = getNextSid();
        newUser.setSid(sid);
        return super.add(newUser);
    }

    private void findNextSid() {
        final String maxSid = (String) usersRepository.findMaxId();
        if (maxSid == null) {
            this.nextSid = 1;
        } else {
            this.nextSid = Integer.parseInt(maxSid.substring(2)) + 1;
        }
        System.out.printf("Next Sid is %d", nextSid);
    }

    protected String getNextSid() {
        final String nextSidStr = this.prefix + String.format("%06d", this.nextSid);
        this.nextSid += 1;
        return nextSidStr;
    }
}
