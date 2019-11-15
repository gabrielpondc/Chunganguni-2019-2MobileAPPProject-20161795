package gujiakai.dotsline.shitu;
import gujiakai.dotsline.mokuai.wanjia;
import java.util.Map;
/**
 * Created by GUJIAKAI on 2019/11
 */
public interface wanjiazhuangtaishitu {
    public void setXianzaikaishi(wanjia Wanjia);
    public void duiwanjijinxinjishu(Map<wanjia,Integer> wanjiaIntegerMap);
    public void wanjiakaishichumo();
    public void shezhiyinjia(wanjia[] yinjia);
}
