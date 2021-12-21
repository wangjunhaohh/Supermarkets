package home;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Rand {
    private static String firstName="赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯咎管卢莫经房裘缪干解应宗宣丁贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄魏加封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭厉戎祖武符刘姜詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲台从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍却璩桑桂濮牛寿通边扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庚终暨居衡步都耿满弘匡国文寇广禄阙东殴殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后江红游竺权逯盖益桓公万俟司马上官欧阳夏侯诸葛闻人东方赫连皇甫尉迟公羊澹台公冶宗政濮阳淳于仲孙太叔申屠公孙乐正轩辕令狐钟离闾丘长孙慕容鲜于宇文司徒司空亓官司寇仉督子车颛孙端木巫马公西漆雕乐正壤驷公良拓拔夹谷宰父谷粱晋楚阎法汝鄢涂钦段干百里东郭南门呼延归海羊舌微生岳帅缑亢况后有琴梁丘左丘东门西门商牟佘佴伯赏南宫墨哈谯笪年爱阳佟第五言福百家姓续";
    //定义女生的名
    private static String girl="秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";
    //定义男生的名
    private static String boy="伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";
    //
    private static String brand[] =new String[]{"盈霆","星西","同真","环宏","财集","金尔","远顺","斯飞","银具","傲惠","风方","开微","特禾","海蓝","赛荣","旭吉","林信","润莱","辰伟","辰艾","艾好","京中","胜通","开超","大铁","江典","旺嘉","鑫好","莱凡","鑫帝","吉优","财帝","高冠","子茂","辉庆","林翔","时凯","盈洋","良耀","生派","博迎","硕腾","同南","拓扬","博卓","诗源","威佩","旺好","发特","德奥","识识","志清","天顿","月百","佳子","悦迎","东鼎","格隆","领子","林子","胜宝","伟环","庆欧","凤铁","贸克","木荣","韦思","真丝","力仕","爱扬","跃日","皇至","财信","营威","腾事","裕海","白美","创奇","瑞辉","讯凌","润雅","森久"};
    private static String goods[] =new String[]{"苹果","梨子","西瓜","猪肉","五花肉","棉被","垫子","书","床","电视","手机","西瓜","面包","奶粉","可乐","杯子","笔","纸"};
    private static String photo[]=new String[]{"Screenshot_20210416_150652.jpg","1616345384125.jpg","d335fd4db13f2c6d6271e6aba756d832.jpeg","Screenshot_20210416_150652.jpg"};
    private static String indentify[]=new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a",
            "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
            "w", "x", "y", "z"};
    public  static int getNum(int start,int end) {//随机返回返回指定范围间的整数
        //Math.random()随机返回0.0至1.0之间的数
        return (int)(Math.random()*(end-start+1)+start);
    }
    public static String getChineseName() {
        int index=getNum(0, firstName.length()-1);//随机取姓氏字符串中的任意位置
        String first=firstName.substring(index, index+1);//获取该位置的姓氏
        int sex=getNum(0, 1);
        String str=boy;//定义名字为男生字符串
        int length=boy.length();//获取男生字符串的长度
        String s="男";
        if(sex==0){//如果随机获取为0，则名字改为女生
            str=girl;
            s="女";
            length=girl.length();
        }
        index=getNum(0,length-1);//随机获取名字的位置
        String second=str.substring(index, index+1);//获取该位置中的名字
        int hasThird=getNum(0,1);//随机获取名字是否有第三个字
        String third="";//默认没有第三个字
        if(hasThird==1){//如果随机获取为1，则有第三个字
            index=getNum(0,length-1);
            third=str.substring(index, index+1);
        }
        return first+second+third+"\t"+s;//返回姓名
    }
    public static String num(){
        int i=getNum(1,999);
        if(i<10){
            return "20194000"+i;
        }else if(10<=i&&i<100){
            return "2019400"+i;
        }else{
            return "201940"+i;
        }
    }
    public static int Num(){
        return getNum(0,10000);
    }
    public static String getTime(){
        int month=getNum(1, 12);
        int day=getNum(1, 31);
        int year=getNum(2000, 2020);
        if(month==2&&year%4==0) {
            day=getNum(1, 29);
        }else if(month==2&&year%4!=0){
            day=getNum(1, 28);
        }
        return year+"/"+getNum(1, 12)+"/"+day+" "+getNum(0, 23)+":"+getNum(0, 59)+":"+getNum(0, 59);
    }
    public static String getSupplier() {
        int a=getNum(0,brand.length-1);
        return brand[a];
    }
    public static String getGoods() {
        int a=getNum(0,goods.length-1);
        return goods[a];
    }
    public static String getPhoto(){
        int a=getNum(0,photo.length-1);
        return photo[a];
    }
    public static String getId(){
        int a=getNum(1000000,9999999);
        return String.valueOf(a);
    }
    public static String getDate(Date start,Date end) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        long startdate = start.getTime();//915120000000l为日期"1999-01-01"的长整型
        long enddate=end.getTime();
        long time = (long) (Math.random() * (enddate - startdate) + startdate);//获取“1999-01-01"至"2005-01-01”的随机长整数
        String rdate = dateFormat.format(new Date(time));//将长整型转换成日期
        return rdate;

    }
    public static String getStrings(int start,int end){
        List list = Arrays.asList(indentify);//将数组转换为集合
        Collections.shuffle(list);  //打乱集合顺序
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)); //将集合转化为字符串
        }
        return sb.toString().substring(start,end );
    }
    public static void main(String[] args) throws ParseException {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
//        Date date = dateFormat.parse("1999-01-01");//生成开始日期
//        Date date2 = dateFormat.parse("1999-12-31");
//        System.out.println(getDate(date, date2));
        System.out.println(getStrings(3,10));
        System.out.println(getStrings(5, 15));
    }
}
