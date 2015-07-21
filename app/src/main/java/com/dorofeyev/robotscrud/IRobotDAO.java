package com.dorofeyev.robotscrud;

import java.util.List;

/**
 * Created by xor on 7/20/15.
 */
public interface IRobotDAO {

    public void create(Robot robot) throws Exception;

    public List<Robot> read() throws Exception;

    public void update(Robot robot) throws Exception;

    public void delete(Robot robot) throws Exception;

    public Robot fetchById(int id) throws Exception;

}
