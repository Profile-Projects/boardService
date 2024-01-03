package com.boardService.service;

import com.boardService.models.BaseModel;
import com.boardService.models.Model;
import com.boardService.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Map;
import java.util.Optional;

public abstract class BaseService<R extends BaseRepository<T, ID>, T, ID> {

    @Autowired
    protected R repo;

    protected Integer nextSid;

    protected String prefix;

    protected String tableName;

    public BaseService(final String prefix, final String tableName) {
        this.prefix = prefix;
        this.tableName = tableName;
//        this.findNextSid();
    }

    public T add(final T t) {
        findNextSid();
        return repo.saveAndFlush(t);
    }

    public Optional<T> get(final ID i) { return repo.findById(i);}

    public void delete(final ID i) { repo.deleteById(i);}

    public T update(final T t, final ID i) {
        if (!repo.existsById(i)) return null;
        return repo.save(t);
    }

    private void findNextSid() {
        final String maxSid = (String) repo.findMaxId(this.tableName);
        if (maxSid == null) {
            this.nextSid = 1;
        } else {
            this.nextSid = Integer.parseInt(maxSid.substring(2)) + 1;
        }
        System.out.printf("Next Sid is %d", nextSid);
    }

    protected String getNextSid() {
        final String nextSidStr = this.prefix + String.format("06%d", this.nextSid);
        this.nextSid += 1;
        return nextSidStr;
    }
}
