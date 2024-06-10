package ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.models.magics;

import static ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.common.ExceptionMessages.*;

public abstract class MagicImpl implements Magic{

    private String name;
    private int bulletsCount;

    protected MagicImpl(String name, int bulletsCount) {
        if(name == null || name.trim().isEmpty()) {
            throw new NullPointerException(INVALID_MAGIC_NAME);
        }
        this.name = name;

        if(bulletsCount < 0)  {
            throw new IllegalArgumentException(INVALID_MAGIC_BULLETS_COUNT);
        }
        this.bulletsCount = bulletsCount;
    }
    protected int doFire(int bullets) {
        if (this.getBulletsCount() < bullets ){
            return 0;
        }

        this.bulletsCount -= bullets;
        return bullets;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getBulletsCount() {
        return bulletsCount;
    }
}
