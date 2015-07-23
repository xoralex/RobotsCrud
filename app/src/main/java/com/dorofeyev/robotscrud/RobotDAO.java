package com.dorofeyev.robotscrud;

import java.util.List;

/**
 * Created by xor on 7/20/15.
 */
public interface RobotDAO {

    public void create(Robot robot);

    public void read(Callback command);

    public void update(Robot robot);

    public void delete(Robot robot);

    public Robot fetchById(int id);

}
