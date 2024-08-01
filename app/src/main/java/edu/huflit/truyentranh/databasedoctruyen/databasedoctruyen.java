package edu.huflit.truyentranh.databasedoctruyen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import edu.huflit.truyentranh.adapter.adapterTruyen;
import edu.huflit.truyentranh.model.Chapter;
import edu.huflit.truyentranh.model.DanhGia;
import edu.huflit.truyentranh.model.TaiKhoan;
import edu.huflit.truyentranh.model.Truyen;

public class databasedoctruyen extends SQLiteOpenHelper {
    //Cơ sở dữ liệu

    //Tên database
    private static String DATABASE_NAME = "doctruyen";

    //Bảng Tài Khoản
    private static String TABLE_TAIKHOAN = "taikhoan";
    private static String ID_TAI_KHOAN = "idtaikhoan";
    private static String TEN_TAI_KHOAN = "tentaikhoan";
    private static String MAT_KHAU = "matkhau";
    private static String PHAN_QUYEN = "phanquyen";
    private static String EMAIL = "email";

    //version
    private static int VERSION = 2;


    //Biến bảng truyện
    private static String TABLE_TRUYEN = "truyen";
    private static String ID_TRUYEN = "idtruyen";
    private static String TEN_TRUYEN = "tieude";
    private static String NOI_DUNG = "noidung";

    private static String CHAPTER = "ischapter";
    private static String IMAGE = "anh";

    //Biến bảng Chapter
    private static String TABLE_CHAPTER = "chapter";
    private static String ID_CHAPTER = "id";
    private static String ID_TRUYEN_CHAPTER = "idtruyen";
    private static String URL_TRUYEN = "url";
    private static String TEN_CHAPTER = "tenchapter";

    // Biến lưu bảng chapter
    private String SQLQuery100 = "CREATE TABLE "+ TABLE_CHAPTER +" ( "+ID_CHAPTER+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +ID_TRUYEN_CHAPTER+" INTEGER, "
            +URL_TRUYEN+" TEXT, "
            +TEN_CHAPTER+" TEXT)";


    //Biến bảng đánh giá
    private static String TABLE_DANH_GIA = "danhgia";
    private static String ID_DANH_GIA = "iddanhgia";
    private static String NOI_DUNG_DANH_GIA = "noidungdanhgia";


    //Context
    private Context context;


    //Biến lưu câu lệnh tạo bảng tài khoản
    private String SQLQuery = "CREATE TABLE "+ TABLE_TAIKHOAN +" ( "+ID_TAI_KHOAN+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +TEN_TAI_KHOAN+" TEXT UNIQUE, "
            +MAT_KHAU+" TEXT, "
            +EMAIL+" TEXT, "
            + PHAN_QUYEN+" INTEGER) ";

    //Biến lưu câu lệnh tạo bảng truyện
    private String SQLQuery1 = "CREATE TABLE "+ TABLE_TRUYEN +" ( "+ID_TRUYEN+" integer primary key AUTOINCREMENT, "
            +TEN_TRUYEN+" TEXT UNIQUE, "
            +NOI_DUNG+" TEXT, "
            +IMAGE+" TEXT, "+ID_TAI_KHOAN+" INTEGER , FOREIGN KEY ( "+ ID_TAI_KHOAN +" ) REFERENCES "+
            TABLE_TAIKHOAN+"("+ID_TAI_KHOAN+"))";

    private String SQLQuery12 = "CREATE TABLE "+ TABLE_DANH_GIA +" ( "+ID_DANH_GIA+" integer primary key AUTOINCREMENT, "
            +NOI_DUNG_DANH_GIA+" TEXT, "
            +TEN_TRUYEN+" TEXT, "
            +TEN_TAI_KHOAN+" TEXT,"
            +ID_TAI_KHOAN+" INTEGER , FOREIGN KEY ( "+ ID_TAI_KHOAN +","+TEN_TAI_KHOAN+" ) REFERENCES "+
            TABLE_TAIKHOAN+"("+ID_TAI_KHOAN+","+TEN_TAI_KHOAN+"))";


    //Insert dữ liệu vào bảng tài khoản
    private String SQLQuery2 = "INSERT INTO TaiKhoan VAlUES (null,'admin','admin','admin@gmail.com',2)";
    private String SQLQuery3 = "INSERT INTO TaiKhoan VAlUES (null,'nhan','nhan','nhan@gmail.com',1)";



    //Insert dữ liệu vào bảng truyện
    private String SQLQuery4 = "INSERT INTO truyen VALUES (null,'Doraemon','Mùa đông đã đến rồi trời lạnh buốt, Doraemon không có gì để ăn cả. Doraemon mặc áo vào rồi ra ngoài kiếm thức ăn. Nó đi mãi đi mãi cuối cùng cũng tìm được hai củ cải trắng. Doraemon reo lên:\n" +
            "\n" +
            "– Ôi, ở đây có hai củ cải trắng liền, mình thật là may mắn!\n" +
            "\n" +
            "Doraemon đói bụng, muốn ăn lắm rồi. Nhưng Doraemon lại nghĩ:\n" +
            "\n" +
            "– Ừm… trời lạnh thế này, chắc Nobita cũng không có cái gì để ăn đâu. Mình phải mang cho Nobita một củ mới được.\n" +
            "\n" +
            "Thế là Doraemon đi sang nhà bạn Nobita nhưng Nobita không có nhà nên Doraemon đặt củ cải lên bàn rồi đi về.\n" +
            "\n" +
            "Tình cờ, Nobita con đi chơi cũng tìm được một củ cải trắng nhưng nó chỉ ăn trước một nửa.\n" +
            "\n" +
            "Về đến nhà, lại thấy có một củ cải trắng ở trên bàn Nobita thèm ăn lắm, nhưng lại nghĩ:\n" +
            "\n" +
            "– Ôi trời lạnh thế này chắc Shizuka không có cái gì để ăn rồi, mình phải mang cho Shizuka mới được.\n" +
            "\n" +
            "Nobita con đến nhà Shizuka nhưng Shizuka lại đi vắng, Nobita bèn đặt củ cải ở trên bàn rồi về.\n" +
            "\n" +
            "Khi Shizuka về nhà, thấy củ cải ở trên bàn, Shizuka ngạc nhiên lắm.\n" +
            "\n" +
            "– Ồ, củ cải trắng ở đâu mà ngon vậy nhỉ. Xuỵt… thích quá. Nhưng chắc trời lạnh thế này, Doraemon cũng không có gì ăn đâu. Mình phải mang sang cho Doraemon mới được.\n" +
            "\n" +
            "Khi Shizuka đến thì Doraemon đang ngủ rất say. Khi tỉnh dậy Doraemon lại thấy trên bàn mình xuất hiện một củ cải trắng.Doraemon vui lắm nó chạy đi gọi các bạn:\n" +
            "\n" +
            "– Bạn Shizuka ơi, bạn Nobita ơi hãy đến nhà tôi, chúng ta cùng ăn củ cải trắng thơm ngon này.\n" +
            "\n" +
            "Thế là cuối cùng, củ cải trắng ấy được chia sẻ cho cả ba người bạn tốt bụng của chúng ta. Các bạn thấy đấy tấm lòng thơm thảo, sẵn sàng sẻ chia của các bạn ấy thật là đáng học tập phải không nào?\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Khi cho đi bạn sẽ nhận lại được nhiều hơn những thứ mình có.','https://cf.shopee.vn/file/2b496b60c912f6a488c7f3936bcd7883',1)";
    private String SQLQuery5 = "INSERT INTO truyen VALUES (null,'Conan','Mùa đông đã đến rồi trời lạnh buốt, Conan không có gì để ăn cả. Conan mặc áo vào rồi ra ngoài kiếm thức ăn. Nó đi mãi đi mãi cuối cùng cũng tìm được hai củ cải trắng. Conan reo lên:\n" +
            "\n" +
            "– Ôi, ở đây có hai củ cải trắng liền, mình thật là may mắn!\n" +
            "\n" +
            "Conan đói bụng, muốn ăn lắm rồi. Nhưng Conan lại nghĩ:\n" +
            "\n" +
            "– Ừm… trời lạnh thế này, chắc Ran cũng không có cái gì để ăn đâu. Mình phải mang cho Ran con một củ mới được.\n" +
            "\n" +
            "Thế là Conan con đi sang nhà bạn Ran nhưng Ran không có nhà nên Conan đặt củ cải lên bàn rồi đi về.\n" +
            "\n" +
            "Tình cờ, Ran đi chơi cũng tìm được một củ cải trắng nhưng nó chỉ ăn trước một nửa.\n" +
            "\n" +
            "Về đến nhà, lại thấy có một củ cải trắng ở trên bàn Ran thèm ăn lắm, nhưng lại nghĩ:\n" +
            "\n" +
            "– Ôi trời lạnh thế này chắc Haibara không có cái gì để ăn rồi, mình phải mang cho Haibara mới được.\n" +
            "\n" +
            "Ran đến nhà Haibara nhưng Haibara lại đi vắng, Ran bèn đặt củ cải ở trên bàn rồi về.\n" +
            "\n" +
            "Khi Haibara về nhà, thấy củ cải ở trên bàn, Haibara ngạc nhiên lắm.\n" +
            "\n" +
            "– Ồ, củ cải trắng ở đâu mà ngon vậy nhỉ. Xuỵt… thích quá. Nhưng chắc trời lạnh thế này, Conan con cũng không có gì ăn đâu. Mình phải mang sang cho Conan mới được.\n" +
            "\n" +
            "Khi Haibara đến thì Conan con đang ngủ rất say. Khi tỉnh dậy Conan lại thấy trên bàn mình xuất hiện một củ cải trắng.Conan vui lắm nó chạy đi gọi các bạn:\n" +
            "\n" +
            "– Bạn Haibara ơi, bạn Ran ơi hãy đến nhà tôi, chúng ta cùng ăn củ cải trắng thơm ngon này.\n" +
            "\n" +
            "Thế là cuối cùng, củ cải trắng ấy được chia sẻ cho cả ba người bạn tốt bụng của chúng ta. Các bạn thấy đấy tấm lòng thơm thảo, sẵn sàng sẻ chia của các bạn ấy thật là đáng học tập phải không nào?\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Khi cho đi bạn sẽ nhận lại được nhiều hơn những thứ mình có.','https://tuoitho.mobi/upload/doc-truyen/conan/anh-dai-dien.jpg',1)";
    private String SQLQuery6 = "INSERT INTO truyen VALUES (null,'One Piece','Luffy đen và Luffy trắng cùng sống trong một khu rừng. Hàng ngày, cả hai thường đến uống nước và tìm cái ăn ở trong khu rừng quen thuộc.\n" +
            "\n" +
            "Một hôm, Luffy trắng đi tìm cái ăn và uống nước suối như mọi khi. Luffy đang mải mê ngặm cỏ, bất chợt một con Sói ở đâu nhảy xổ ra. Sói quát hỏi:\n" +
            "\n" +
            "- Luffy kia! Mi đi đâu?\n" +
            "\n" +
            "Luffy trắng sợ rúm cả người, lắp bắp:\n" +
            "\n" +
            "– Dạ, dạ, tôi đi tìm… tìm cỏ non và…và uống nước suối ạ!\n" +
            "\n" +
            "Sói lại quát hỏi:\n" +
            "\n" +
            "– Mi có gì ở chân?\n" +
            "\n" +
            "– Dạ, dạ, chân của tôi có móng ạ…ạ!\n" +
            "\n" +
            "– Trên đầu mi có gì?\n" +
            "\n" +
            "– Dạ, dạ, trên đầu tôi có đôi sừng mới nhú…\n" +
            "\n" +
            "Sói càng quát to hơn:\n" +
            "\n" +
            "– Trái tim mi thế nào?\n" +
            "\n" +
            "– Ôi, ôi, trái…trái tim tôi đang run sợ…sợ…\n" +
            "\n" +
            "– Hahaha…\n" +
            "\n" +
            "Sói cười vang rồi ăn thịt chú Luffy trắng tội nghiệp\n" +
            "\n" +
            "Luffy đen cũng đi tới khu rừng để ăn cỏ non và uống nước suối. Đang tha thẩn ngặm cỏ, chợt Sói xuất hiện, nó quát hỏi:\n" +
            "\n" +
            "– Luffy kia, mi đi đâu?\n" +
            "\n" +
            "Luffy đen nhìn con Sói từ đầu tới chân rồi ngước cổ trả lời:\n" +
            "\n" +
            "– Ta đi tìm kẻ nào thích gây sự đây!\n" +
            "\n" +
            "Sói bị bất ngờ, nó hỏi tiếp:\n" +
            "\n" +
            "– Thế dưới chân mi có gì?\n" +
            "\n" +
            "– Chân thép của ta có móng bằng đồng.\n" +
            "\n" +
            "– Thế…thế…trên đầu mi có gì?\n" +
            "\n" +
            "– Trên đầu của ta có đôi sừng bằng kim cương!\n" +
            "\n" +
            "Sói sợ lắm rồi, nhưng vẫn cố hỏi:\n" +
            "\n" +
            "– Mi…mi…trái tim mi thế nào?\n" +
            "\n" +
            "Luffy đen dõng dạc trả lời:\n" +
            "\n" +
            "– Trái tim thép của ta bảo ta rằng: hãy cắm đôi sừng kim cương vào đầu Sói. Nào, Sói hãy lại đây.\n" +
            "\n" +
            "Ôi trời, sợ quá, con Sói ba chân bốn cẳng chạy biến vào rừng, từ đó không ai trông thấy nó lởn vởn ở khu rừng đó nữa.\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Qua câu chuyện ngụ ngôn trên, bạn có thể truyền tải nhiều thông điệp khác nhau cho bé hiểu. Chẳng hạn như biết cách ứng xử trước các tình huống khó, nguy hiểm, lạc quan và bản lĩnh để xử lý vấn đề.','https://www.cgv.vn/media/catalog/product/cache/1/image/c5f0a1eff4c394a251036189ccddaacd/p/o/poster_one_piece_3_-_final_main_version_1_.jpg',1)";
    private String SQLQuery7 = "INSERT INTO truyen VALUES (null,'Dragon ball','Một chú bé chăn cừu cho chủ thả cừu gần một khu rừng rậm cách làng không xa lắm. Chăn cừu được ít lâu, chú cảm thấy công việc chăn cừu thực là nhàm chán. Tất cả mọi việc chú có thể làm để giải khuây là nói chuyện với con chó hoặc thổi chiếc sáo chăn cừu của mình.\n" +
            "\n" +
            "Một hôm, trong lúc đang ngắm nhìn đàn cừu và cánh rừng yên tĩnh chú bé chợt nhớ tới lời chủ của chú từng dặn rằng khi sói tấn công cừu thì phải kêu cứu để dân làng nghe thấy và đánh đuổi nó đi.\n" +
            "\n" +
            "Thế là chú nghĩ ra một trò chơi cho đỡ buồn. Mặc dù chẳng thấy con sói nào, chú cứ chạy về làng và la to:\n" +
            "\n" +
            "– Sói ! Sói !\n" +
            "\n" +
            "Đúng như chú nghĩ, dân làng nghe thấy tiếng kêu bỏ cả việc làm và tức tốc chạy ra cánh đồng. Nhưng khi đến nơi, họ chẳng thấy sói đâu, chỉ thấy chú bé ôm bụng cười ngặt nghẽo vì đã lừa được họ.\n" +
            "\n" +
            "Ít ngày sau chú bé chăn cừu lần nữa lại la lên:\n" +
            "\n" +
            "– “Sói ! Sói !”.\n" +
            "\n" +
            "Và một lần nữa dân làng lại chạy ra giúp chú. Nhưng họ lại chẳng thấy con sói nào, chỉ thấy chú bé chăn cừu nghịch ngợm ôm bụng cười khoái chí.\n" +
            "\n" +
            "Thế rồi vào một buổi chiều nọ, khi mặt trời lặn xuống sau cánh rừng và bóng tối bắt đầu phủ đầy lên cánh đồng, một con sói thực sự xuất hiện. Nó nấp sau bụi cây rình bắt những con cừu béo non. Bỗng sói phóng vút ra chộp lấy một chú cừu tội nghiệp. Thấy sói cả đàn cừu sợ hãi chạy toán loạn, chú bé chăn cừu cũng hoảng loạn vô cùng.\n" +
            "\n" +
            "Quá hoảng sợ, chú bé chăn cừu vội chạy về làng và la to:\n" +
            "\n" +
            "– “Sói ! Sói !”.\n" +
            "\n" +
            "Nhưng mặc dù dân làng có nghe tiếng chú kêu, không một ai chạy ra để giúp chú như những lần trước cả. Vì mọi người nghĩ chú lại bày trò nói dối như trước.\n" +
            "\n" +
            "Thế là sói thỏa sức bắt mồi, giết chết rất nhiều cừu của chú bé. Sau khi đã chén no nê, nó biến mất vào rừng rậm. Chú bé buồn bã ngồi giữa đồng cỏ, lòng đầy hối hận về hành động nói dối của mình và hậu quả của trò đùa dại dột gây ra.\n" +
            "\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Nói dối là một tật xấu. Người hay nói dối ngay cả khi nói thật cũng không ai tin.','https://m.media-amazon.com/images/M/MV5BYzI0YjYxY2UtNzRjNS00NTZiLTgzMDItNGEzMjU5MmE0ZWJmXkEyXkFqcGdeQXVyNjAwNDUxODI@._V1_FMjpg_UY3000_.jpg',1)";
    private String SQLQuery8 = "INSERT INTO truyen VALUES (null,'Inuyasha','Ngày xửa ngày xưa, xưa lắm rồi khi mà muôn thú, cây cỏ, con người còn trò chuyện được với nhau. Trên đồng cỏ rậm ven khu làng có một loài cây gọi là cây đa. Đó là một thứ cây to, khỏe, lá của nó rậm rạp đến nỗi không một tia nắng nào có thể lọt qua được. Vào những ngày trời nắng nóng người ta thường nghỉ chân một lát và trò chuyện hàn huyên cùng cây dưới bóng cây mát rượi. Mọi người ai cũng biết rằng cây đa rất thông thái vì cây đã có tuổi, đã từng trải.\n" +
            "\n" +
            "\n" +
            "Một hôm, có một cậu bé chăn cừu ngồi nghỉ mát dưới gốc cây sau một ngày dài phơi mình dưới nắng cậu bé thấy người mệt mỏi và nóng bức. Một làn gió mơn man thổi thoa nhẹ lên tấm thân mỏi mệt của chú bé. Cậu bé bắt đầu thấy buồn ngủ. Vừa đặt mình xuống cậu bé bỗng ngước mắt nhìn lên những cành cây. Bấy giờ cậu bé bỗng thấy mình thật kiêu hãnh, cậu vẫn thường hay khoe với mọi người rằng cậu có tài chăn cừu và đàn cừu của cậu nhờ vậy mà lớn rất nhanh. Khi cậu bé phát hiện ra cây đa chỉ có những chùm quả rất nhỏ, nó bắt đầu thấy ngạc nhiên. Cậu bắt đầu chế giễu: hư, một cái cây to khỏe thế này mà làm sao chỉ có những bông hoa những chùm quả bé tí tẹo thế kia, mọi người vẫn bảo là cái cây này thông thái lắm kia mà nhưng làm sao nó có thể thông thái khi mà quả của nó chỉ toàn bé xíu như vậy. Dĩ nhiên là cây đa nghe hết những lời của cậu bé nhưng cây vẫn im lặng và cành lá chỉ khẽ rung rinh đủ để cho gió cất lên khúc hát ru êm dịu. Cậu bé bắt đầu ngủ, cậu ngáy o o…. Cốc.\n" +
            "Quả đa nhỏ rụng chính giữa trán của cậu bé, nó bừng tỉnh nhưng càu nhàu: “Gừm… người ta vừa mới chợp mắt được có một tí”, rồi nó nhặt quả đa lên chưa hết chưa biết định làm gì với quả đa này bỗng nhiên cậu bé nghe thấy có tiếng cười khúc khích, cậu nghe thấy cây hỏi:\n" +
            "– Có đau không ?.\n" +
            "– Không nhưng mà làm người ta mất cả giấc ngủ .\n" +
            "– Đó là bài học cho cậu bé to đầu đấy. Cậu chẳng vừa nhạo tôi là chỉ sinh ra toàn những quả nhỏ xíu là gì.\n" +
            "– Tôi nhạo đấy tại sao người đời lại bảo bác là thông thái được nhỉ? Phá giấc ngủ trưa của người khác! Thế cũng là thông minh chắc!.\n" +
            "Cây cười và nói: này này anh bạn anh hãy nghe đây những chiếc lá của tôi cho cậu bóng mát để cậu lấy chỗ nghỉ ngơi. Ừ thì cứ cho là quả của tôi nó bé đi chăng nữa nhưng chẳng lẽ cậu không thấy rằng tạo hóa hoạt động rất hoàn chỉnh đó sao. Cậu thử tưởng tượng xem, nếu quả của tôi to như quả dừa thì điều gì sẽ xảy ra khi nó rơi vào đầu cậu.\n" +
            "Cậu bé im thin thít: ừ nhở. Cậu chưa hề nghĩ đến điều này bao giờ cả.\n" +
            "Cây lại nhẹ nhàng tiếp lời:\n" +
            "– Những người khiêm tốn có thể học hỏi rất nhiều điều từ việc quan sát những vật xung quanh đấy cậu bé ạ.\n" +
            "– Vâng bác đa bác cứ nói tiếp đi.\n" +
            "– Cậu hãy bắt đầu làm bạn với những gì ở quanh cậu. Chúng ta tất cả đều cần tới nhau. Cậu cứ nhìn bầy ong kia mà xem. Nhờ có ong mà hoa của tôi mới có thể trở thành quả. Thế còn bầy chim kia thì sao. Chúng làm tổ ngay giữa tán lá của tôi đây này. Những con chim bố mẹ kia phải làm việc vất vả cả ngày để bắt sâu nuôi con và cậu có biết việc làm đó có ý nghĩa gì với tôi không?.\n" +
            "– Không, có ý nghĩa gì vậy hả bác?.\n" +
            "– Sâu ăn lá chính vậy loài chim kia chính là những người bạn của tôi. Chúng còn giúp cả cậu nữa đấy, sở dĩ cừu của cậu có đủ lá và cỏ để ăn là vì chim chóc đã tiêu diệt hết các loài côn trùng và sâu bọ. Và chưa hết đâu cậu bé ạ!.\n" +
            "– Còn gì thế nữa hả bác đa.\n" +
            "– Cậu hãy nhìn xuống chân mình mà xem, những chiếc lá rụng tạo thành lớp thảm mục, những con sâu đào đất ngoi lên để ăn lá, chúng đào đất thành những lỗ nhỏ, nhờ đó không khí có thể vào được trong đất. Có không khí trong đất nên bộ rễ của tôi mới khỏe thế nào đấy. Rễ khỏe nên tôi cũng khỏe hơn. Nào thế bây giờ cậu trẻ đã hiểu chưa?.\n" +
            "– Cháu hiểu rồi thưa bác. Bác tha lỗi cho cháu nhé vì đã cười nhạo bác bác đa ạ.\n" +
            "– Không sao bây giờ cháu hãy ra dắt cừu về đi.\n" +
            "\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Có thể cậu bé chăn cừu không phải ngay sau đó sẽ trở nên khiêm tốn, học hỏi luôn được nhưng rõ ràng là cậu đã nhận ra người ta không thể sống lẻ loi được.','https://i.pinimg.com/236x/58/95/9b/58959b6905eb4f9011ab60977d3d7a29.jpg',1)";


    private String SQLQuery9 = "INSERT INTO truyen VALUES (null,'Sự tích trầu cau','Vẽ tranh đề tài – vẽ trang trí bìa sách – truyện cổ tích Sự tích trầu cau.\n" +
            "\n" +
            "\n" +
            "Một hôm, có một cậu bé chăn cừu ngồi nghỉ mát dưới gốc cây sau một ngày dài phơi mình dưới nắng cậu bé thấy người mệt mỏi và nóng bức. Một làn gió mơn man thổi thoa nhẹ lên tấm thân mỏi mệt của chú bé. Cậu bé bắt đầu thấy buồn ngủ. Vừa đặt mình xuống cậu bé bỗng ngước mắt nhìn lên những cành cây. Bấy giờ cậu bé bỗng thấy mình thật kiêu hãnh, cậu vẫn thường hay khoe với mọi người rằng cậu có tài chăn cừu và đàn cừu của cậu nhờ vậy mà lớn rất nhanh. Khi cậu bé phát hiện ra cây đa chỉ có những chùm quả rất nhỏ, nó bắt đầu thấy ngạc nhiên. Cậu bắt đầu chế giễu: hư, một cái cây to khỏe thế này mà làm sao chỉ có những bông hoa những chùm quả bé tí tẹo thế kia, mọi người vẫn bảo là cái cây này thông thái lắm kia mà nhưng làm sao nó có thể thông thái khi mà quả của nó chỉ toàn bé xíu như vậy. Dĩ nhiên là cây đa nghe hết những lời của cậu bé nhưng cây vẫn im lặng và cành lá chỉ khẽ rung rinh đủ để cho gió cất lên khúc hát ru êm dịu. Cậu bé bắt đầu ngủ, cậu ngáy o o…. Cốc.\n" +
            "Quả đa nhỏ rụng chính giữa trán của cậu bé, nó bừng tỉnh nhưng càu nhàu: “Gừm… người ta vừa mới chợp mắt được có một tí”, rồi nó nhặt quả đa lên chưa hết chưa biết định làm gì với quả đa này bỗng nhiên cậu bé nghe thấy có tiếng cười khúc khích, cậu nghe thấy cây hỏi:\n" +
            "– Có đau không ?.\n" +
            "– Không nhưng mà làm người ta mất cả giấc ngủ .\n" +
            "– Đó là bài học cho cậu bé to đầu đấy. Cậu chẳng vừa nhạo tôi là chỉ sinh ra toàn những quả nhỏ xíu là gì.\n" +
            "– Tôi nhạo đấy tại sao người đời lại bảo bác là thông thái được nhỉ? Phá giấc ngủ trưa của người khác! Thế cũng là thông minh chắc!.\n" +
            "Cây cười và nói: này này anh bạn anh hãy nghe đây những chiếc lá của tôi cho cậu bóng mát để cậu lấy chỗ nghỉ ngơi. Ừ thì cứ cho là quả của tôi nó bé đi chăng nữa nhưng chẳng lẽ cậu không thấy rằng tạo hóa hoạt động rất hoàn chỉnh đó sao. Cậu thử tưởng tượng xem, nếu quả của tôi to như quả dừa thì điều gì sẽ xảy ra khi nó rơi vào đầu cậu.\n" +
            "Cậu bé im thin thít: ừ nhở. Cậu chưa hề nghĩ đến điều này bao giờ cả.\n" +
            "Cây lại nhẹ nhàng tiếp lời:\n" +
            "– Những người khiêm tốn có thể học hỏi rất nhiều điều từ việc quan sát những vật xung quanh đấy cậu bé ạ.\n" +
            "– Vâng bác đa bác cứ nói tiếp đi.\n" +
            "– Cậu hãy bắt đầu làm bạn với những gì ở quanh cậu. Chúng ta tất cả đều cần tới nhau. Cậu cứ nhìn bầy ong kia mà xem. Nhờ có ong mà hoa của tôi mới có thể trở thành quả. Thế còn bầy chim kia thì sao. Chúng làm tổ ngay giữa tán lá của tôi đây này. Những con chim bố mẹ kia phải làm việc vất vả cả ngày để bắt sâu nuôi con và cậu có biết việc làm đó có ý nghĩa gì với tôi không?.\n" +
            "– Không, có ý nghĩa gì vậy hả bác?.\n" +
            "– Sâu ăn lá chính vậy loài chim kia chính là những người bạn của tôi. Chúng còn giúp cả cậu nữa đấy, sở dĩ cừu của cậu có đủ lá và cỏ để ăn là vì chim chóc đã tiêu diệt hết các loài côn trùng và sâu bọ. Và chưa hết đâu cậu bé ạ!.\n" +
            "– Còn gì thế nữa hả bác đa.\n" +
            "– Cậu hãy nhìn xuống chân mình mà xem, những chiếc lá rụng tạo thành lớp thảm mục, những con sâu đào đất ngoi lên để ăn lá, chúng đào đất thành những lỗ nhỏ, nhờ đó không khí có thể vào được trong đất. Có không khí trong đất nên bộ rễ của tôi mới khỏe thế nào đấy. Rễ khỏe nên tôi cũng khỏe hơn. Nào thế bây giờ cậu trẻ đã hiểu chưa?.\n" +
            "– Cháu hiểu rồi thưa bác. Bác tha lỗi cho cháu nhé vì đã cười nhạo bác bác đa ạ.\n" +
            "– Không sao bây giờ cháu hãy ra dắt cừu về đi.\n" +
            "\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Có thể cậu bé chăn cừu không phải ngay sau đó sẽ trở nên khiêm tốn, học hỏi luôn được nhưng rõ ràng là cậu đã nhận ra người ta không thể sống lẻ loi được.','https://i.ytimg.com/vi/mBu_IMWcQJ0/hqdefault.jpg',1)";
    private String SQLQuery10 = "INSERT INTO truyen VALUES (null,'Manga2','Vương gia bá đạo và thê tử thông minh, lần đầu gặp mặt, cô ta trị thương cho huynh ấy, lấy chi phí trị thương khoảng vài tram lượng\n" +
            "\n" +
            "\n" +
            "Một hôm, có một cậu bé chăn cừu ngồi nghỉ mát dưới gốc cây sau một ngày dài phơi mình dưới nắng cậu bé thấy người mệt mỏi và nóng bức. Một làn gió mơn man thổi thoa nhẹ lên tấm thân mỏi mệt của chú bé. Cậu bé bắt đầu thấy buồn ngủ. Vừa đặt mình xuống cậu bé bỗng ngước mắt nhìn lên những cành cây. Bấy giờ cậu bé bỗng thấy mình thật kiêu hãnh, cậu vẫn thường hay khoe với mọi người rằng cậu có tài chăn cừu và đàn cừu của cậu nhờ vậy mà lớn rất nhanh. Khi cậu bé phát hiện ra cây đa chỉ có những chùm quả rất nhỏ, nó bắt đầu thấy ngạc nhiên. Cậu bắt đầu chế giễu: hư, một cái cây to khỏe thế này mà làm sao chỉ có những bông hoa những chùm quả bé tí tẹo thế kia, mọi người vẫn bảo là cái cây này thông thái lắm kia mà nhưng làm sao nó có thể thông thái khi mà quả của nó chỉ toàn bé xíu như vậy. Dĩ nhiên là cây đa nghe hết những lời của cậu bé nhưng cây vẫn im lặng và cành lá chỉ khẽ rung rinh đủ để cho gió cất lên khúc hát ru êm dịu. Cậu bé bắt đầu ngủ, cậu ngáy o o…. Cốc.\n" +
            "Quả đa nhỏ rụng chính giữa trán của cậu bé, nó bừng tỉnh nhưng càu nhàu: “Gừm… người ta vừa mới chợp mắt được có một tí”, rồi nó nhặt quả đa lên chưa hết chưa biết định làm gì với quả đa này bỗng nhiên cậu bé nghe thấy có tiếng cười khúc khích, cậu nghe thấy cây hỏi:\n" +
            "– Có đau không ?.\n" +
            "– Không nhưng mà làm người ta mất cả giấc ngủ .\n" +
            "– Đó là bài học cho cậu bé to đầu đấy. Cậu chẳng vừa nhạo tôi là chỉ sinh ra toàn những quả nhỏ xíu là gì.\n" +
            "– Tôi nhạo đấy tại sao người đời lại bảo bác là thông thái được nhỉ? Phá giấc ngủ trưa của người khác! Thế cũng là thông minh chắc!.\n" +
            "Cây cười và nói: này này anh bạn anh hãy nghe đây những chiếc lá của tôi cho cậu bóng mát để cậu lấy chỗ nghỉ ngơi. Ừ thì cứ cho là quả của tôi nó bé đi chăng nữa nhưng chẳng lẽ cậu không thấy rằng tạo hóa hoạt động rất hoàn chỉnh đó sao. Cậu thử tưởng tượng xem, nếu quả của tôi to như quả dừa thì điều gì sẽ xảy ra khi nó rơi vào đầu cậu.\n" +
            "Cậu bé im thin thít: ừ nhở. Cậu chưa hề nghĩ đến điều này bao giờ cả.\n" +
            "Cây lại nhẹ nhàng tiếp lời:\n" +
            "– Những người khiêm tốn có thể học hỏi rất nhiều điều từ việc quan sát những vật xung quanh đấy cậu bé ạ.\n" +
            "– Vâng bác đa bác cứ nói tiếp đi.\n" +
            "– Cậu hãy bắt đầu làm bạn với những gì ở quanh cậu. Chúng ta tất cả đều cần tới nhau. Cậu cứ nhìn bầy ong kia mà xem. Nhờ có ong mà hoa của tôi mới có thể trở thành quả. Thế còn bầy chim kia thì sao. Chúng làm tổ ngay giữa tán lá của tôi đây này. Những con chim bố mẹ kia phải làm việc vất vả cả ngày để bắt sâu nuôi con và cậu có biết việc làm đó có ý nghĩa gì với tôi không?.\n" +
            "– Không, có ý nghĩa gì vậy hả bác?.\n" +
            "– Sâu ăn lá chính vậy loài chim kia chính là những người bạn của tôi. Chúng còn giúp cả cậu nữa đấy, sở dĩ cừu của cậu có đủ lá và cỏ để ăn là vì chim chóc đã tiêu diệt hết các loài côn trùng và sâu bọ. Và chưa hết đâu cậu bé ạ!.\n" +
            "– Còn gì thế nữa hả bác đa.\n" +
            "– Cậu hãy nhìn xuống chân mình mà xem, những chiếc lá rụng tạo thành lớp thảm mục, những con sâu đào đất ngoi lên để ăn lá, chúng đào đất thành những lỗ nhỏ, nhờ đó không khí có thể vào được trong đất. Có không khí trong đất nên bộ rễ của tôi mới khỏe thế nào đấy. Rễ khỏe nên tôi cũng khỏe hơn. Nào thế bây giờ cậu trẻ đã hiểu chưa?.\n" +
            "– Cháu hiểu rồi thưa bác. Bác tha lỗi cho cháu nhé vì đã cười nhạo bác bác đa ạ.\n" +
            "– Không sao bây giờ cháu hãy ra dắt cừu về đi.\n" +
            "\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Có thể cậu bé chăn cừu không phải ngay sau đó sẽ trở nên khiêm tốn, học hỏi luôn được nhưng rõ ràng là cậu đã nhận ra người ta không thể sống lẻ loi được.','https://www.wikihow.vn/%C4%90%E1%BB%8Dc-truy%E1%BB%87n-tranh-Nh%E1%BA%ADt-b%E1%BA%A3n-%28manga%29#/T%E1%BA%ADp_tin:Read-Manga-Step-1-Version-2.jpg',1)";
    private String SQLQuery8888 = "INSERT INTO truyen VALUES (null,'Bạch Tuyết và bảy chú lùn','CỔ TÍCH VÀNG - NHỮNG CÂU CHUYỆN THÔNG THÁI - NÀNG BẠCH TUYẾT VÀ BẢY CHÚ LÙN.\n" +
            "\n" +
            "\n" +
            "Một hôm, có một cậu bé chăn cừu ngồi nghỉ mát dưới gốc cây sau một ngày dài phơi mình dưới nắng cậu bé thấy người mệt mỏi và nóng bức. Một làn gió mơn man thổi thoa nhẹ lên tấm thân mỏi mệt của chú bé. Cậu bé bắt đầu thấy buồn ngủ. Vừa đặt mình xuống cậu bé bỗng ngước mắt nhìn lên những cành cây. Bấy giờ cậu bé bỗng thấy mình thật kiêu hãnh, cậu vẫn thường hay khoe với mọi người rằng cậu có tài chăn cừu và đàn cừu của cậu nhờ vậy mà lớn rất nhanh. Khi cậu bé phát hiện ra cây đa chỉ có những chùm quả rất nhỏ, nó bắt đầu thấy ngạc nhiên. Cậu bắt đầu chế giễu: hư, một cái cây to khỏe thế này mà làm sao chỉ có những bông hoa những chùm quả bé tí tẹo thế kia, mọi người vẫn bảo là cái cây này thông thái lắm kia mà nhưng làm sao nó có thể thông thái khi mà quả của nó chỉ toàn bé xíu như vậy. Dĩ nhiên là cây đa nghe hết những lời của cậu bé nhưng cây vẫn im lặng và cành lá chỉ khẽ rung rinh đủ để cho gió cất lên khúc hát ru êm dịu. Cậu bé bắt đầu ngủ, cậu ngáy o o…. Cốc.\n" +
            "Quả đa nhỏ rụng chính giữa trán của cậu bé, nó bừng tỉnh nhưng càu nhàu: “Gừm… người ta vừa mới chợp mắt được có một tí”, rồi nó nhặt quả đa lên chưa hết chưa biết định làm gì với quả đa này bỗng nhiên cậu bé nghe thấy có tiếng cười khúc khích, cậu nghe thấy cây hỏi:\n" +
            "– Có đau không ?.\n" +
            "– Không nhưng mà làm người ta mất cả giấc ngủ .\n" +
            "– Đó là bài học cho cậu bé to đầu đấy. Cậu chẳng vừa nhạo tôi là chỉ sinh ra toàn những quả nhỏ xíu là gì.\n" +
            "– Tôi nhạo đấy tại sao người đời lại bảo bác là thông thái được nhỉ? Phá giấc ngủ trưa của người khác! Thế cũng là thông minh chắc!.\n" +
            "Cây cười và nói: này này anh bạn anh hãy nghe đây những chiếc lá của tôi cho cậu bóng mát để cậu lấy chỗ nghỉ ngơi. Ừ thì cứ cho là quả của tôi nó bé đi chăng nữa nhưng chẳng lẽ cậu không thấy rằng tạo hóa hoạt động rất hoàn chỉnh đó sao. Cậu thử tưởng tượng xem, nếu quả của tôi to như quả dừa thì điều gì sẽ xảy ra khi nó rơi vào đầu cậu.\n" +
            "Cậu bé im thin thít: ừ nhở. Cậu chưa hề nghĩ đến điều này bao giờ cả.\n" +
            "Cây lại nhẹ nhàng tiếp lời:\n" +
            "– Những người khiêm tốn có thể học hỏi rất nhiều điều từ việc quan sát những vật xung quanh đấy cậu bé ạ.\n" +
            "– Vâng bác đa bác cứ nói tiếp đi.\n" +
            "– Cậu hãy bắt đầu làm bạn với những gì ở quanh cậu. Chúng ta tất cả đều cần tới nhau. Cậu cứ nhìn bầy ong kia mà xem. Nhờ có ong mà hoa của tôi mới có thể trở thành quả. Thế còn bầy chim kia thì sao. Chúng làm tổ ngay giữa tán lá của tôi đây này. Những con chim bố mẹ kia phải làm việc vất vả cả ngày để bắt sâu nuôi con và cậu có biết việc làm đó có ý nghĩa gì với tôi không?.\n" +
            "– Không, có ý nghĩa gì vậy hả bác?.\n" +
            "– Sâu ăn lá chính vậy loài chim kia chính là những người bạn của tôi. Chúng còn giúp cả cậu nữa đấy, sở dĩ cừu của cậu có đủ lá và cỏ để ăn là vì chim chóc đã tiêu diệt hết các loài côn trùng và sâu bọ. Và chưa hết đâu cậu bé ạ!.\n" +
            "– Còn gì thế nữa hả bác đa.\n" +
            "– Cậu hãy nhìn xuống chân mình mà xem, những chiếc lá rụng tạo thành lớp thảm mục, những con sâu đào đất ngoi lên để ăn lá, chúng đào đất thành những lỗ nhỏ, nhờ đó không khí có thể vào được trong đất. Có không khí trong đất nên bộ rễ của tôi mới khỏe thế nào đấy. Rễ khỏe nên tôi cũng khỏe hơn. Nào thế bây giờ cậu trẻ đã hiểu chưa?.\n" +
            "– Cháu hiểu rồi thưa bác. Bác tha lỗi cho cháu nhé vì đã cười nhạo bác bác đa ạ.\n" +
            "– Không sao bây giờ cháu hãy ra dắt cừu về đi.\n" +
            "\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Có thể cậu bé chăn cừu không phải ngay sau đó sẽ trở nên khiêm tốn, học hỏi luôn được nhưng rõ ràng là cậu đã nhận ra người ta không thể sống lẻ loi được.','https://salt.tikicdn.com/media/catalog/product%2Fi%2Fm%2Fimg825.u2487.d20160823.t145124.363585.jpg',1)";
    private String SQLQuery11 = "INSERT INTO truyen VALUES (null,'Tấm Cám','tấm Cám là chuyện cổ tích quen thuộc với dân tộc Việt Nam, mang tính giáo dục về sự yêu ghét và tính thiện ác dành cho các bé\n" +
            "\n" +
            "\n" +
            "Một hôm, có một cậu bé chăn cừu ngồi nghỉ mát dưới gốc cây sau một ngày dài phơi mình dưới nắng cậu bé thấy người mệt mỏi và nóng bức. Một làn gió mơn man thổi thoa nhẹ lên tấm thân mỏi mệt của chú bé. Cậu bé bắt đầu thấy buồn ngủ. Vừa đặt mình xuống cậu bé bỗng ngước mắt nhìn lên những cành cây. Bấy giờ cậu bé bỗng thấy mình thật kiêu hãnh, cậu vẫn thường hay khoe với mọi người rằng cậu có tài chăn cừu và đàn cừu của cậu nhờ vậy mà lớn rất nhanh. Khi cậu bé phát hiện ra cây đa chỉ có những chùm quả rất nhỏ, nó bắt đầu thấy ngạc nhiên. Cậu bắt đầu chế giễu: hư, một cái cây to khỏe thế này mà làm sao chỉ có những bông hoa những chùm quả bé tí tẹo thế kia, mọi người vẫn bảo là cái cây này thông thái lắm kia mà nhưng làm sao nó có thể thông thái khi mà quả của nó chỉ toàn bé xíu như vậy. Dĩ nhiên là cây đa nghe hết những lời của cậu bé nhưng cây vẫn im lặng và cành lá chỉ khẽ rung rinh đủ để cho gió cất lên khúc hát ru êm dịu. Cậu bé bắt đầu ngủ, cậu ngáy o o…. Cốc.\n" +
            "Quả đa nhỏ rụng chính giữa trán của cậu bé, nó bừng tỉnh nhưng càu nhàu: “Gừm… người ta vừa mới chợp mắt được có một tí”, rồi nó nhặt quả đa lên chưa hết chưa biết định làm gì với quả đa này bỗng nhiên cậu bé nghe thấy có tiếng cười khúc khích, cậu nghe thấy cây hỏi:\n" +
            "– Có đau không ?.\n" +
            "– Không nhưng mà làm người ta mất cả giấc ngủ .\n" +
            "– Đó là bài học cho cậu bé to đầu đấy. Cậu chẳng vừa nhạo tôi là chỉ sinh ra toàn những quả nhỏ xíu là gì.\n" +
            "– Tôi nhạo đấy tại sao người đời lại bảo bác là thông thái được nhỉ? Phá giấc ngủ trưa của người khác! Thế cũng là thông minh chắc!.\n" +
            "Cây cười và nói: này này anh bạn anh hãy nghe đây những chiếc lá của tôi cho cậu bóng mát để cậu lấy chỗ nghỉ ngơi. Ừ thì cứ cho là quả của tôi nó bé đi chăng nữa nhưng chẳng lẽ cậu không thấy rằng tạo hóa hoạt động rất hoàn chỉnh đó sao. Cậu thử tưởng tượng xem, nếu quả của tôi to như quả dừa thì điều gì sẽ xảy ra khi nó rơi vào đầu cậu.\n" +
            "Cậu bé im thin thít: ừ nhở. Cậu chưa hề nghĩ đến điều này bao giờ cả.\n" +
            "Cây lại nhẹ nhàng tiếp lời:\n" +
            "– Những người khiêm tốn có thể học hỏi rất nhiều điều từ việc quan sát những vật xung quanh đấy cậu bé ạ.\n" +
            "– Vâng bác đa bác cứ nói tiếp đi.\n" +
            "– Cậu hãy bắt đầu làm bạn với những gì ở quanh cậu. Chúng ta tất cả đều cần tới nhau. Cậu cứ nhìn bầy ong kia mà xem. Nhờ có ong mà hoa của tôi mới có thể trở thành quả. Thế còn bầy chim kia thì sao. Chúng làm tổ ngay giữa tán lá của tôi đây này. Những con chim bố mẹ kia phải làm việc vất vả cả ngày để bắt sâu nuôi con và cậu có biết việc làm đó có ý nghĩa gì với tôi không?.\n" +
            "– Không, có ý nghĩa gì vậy hả bác?.\n" +
            "– Sâu ăn lá chính vậy loài chim kia chính là những người bạn của tôi. Chúng còn giúp cả cậu nữa đấy, sở dĩ cừu của cậu có đủ lá và cỏ để ăn là vì chim chóc đã tiêu diệt hết các loài côn trùng và sâu bọ. Và chưa hết đâu cậu bé ạ!.\n" +
            "– Còn gì thế nữa hả bác đa.\n" +
            "– Cậu hãy nhìn xuống chân mình mà xem, những chiếc lá rụng tạo thành lớp thảm mục, những con sâu đào đất ngoi lên để ăn lá, chúng đào đất thành những lỗ nhỏ, nhờ đó không khí có thể vào được trong đất. Có không khí trong đất nên bộ rễ của tôi mới khỏe thế nào đấy. Rễ khỏe nên tôi cũng khỏe hơn. Nào thế bây giờ cậu trẻ đã hiểu chưa?.\n" +
            "– Cháu hiểu rồi thưa bác. Bác tha lỗi cho cháu nhé vì đã cười nhạo bác bác đa ạ.\n" +
            "– Không sao bây giờ cháu hãy ra dắt cừu về đi.\n" +
            "\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Có thể cậu bé chăn cừu không phải ngay sau đó sẽ trở nên khiêm tốn, học hỏi luôn được nhưng rõ ràng là cậu đã nhận ra người ta không thể sống lẻ loi được.','https://www.nxbtre.com.vn/Images/Book/NXBTreStoryFull_25462016_014636.jpg',1)";

    private String SQLQuery001 = "INSERT INTO truyen VALUES (null,' Demokurashi','Truyện được viết hoàn toàn bằng tiếng Nhật thông thường, có hán tự và phiên âm Hán tự sang Hiragana giúp bạn học dễ đọc và nắm bắt nội dung \n" +
            "\n" +
            "\n" +
            "Một hôm, có một cậu bé chăn cừu ngồi nghỉ mát dưới gốc cây sau một ngày dài phơi mình dưới nắng cậu bé thấy người mệt mỏi và nóng bức. Một làn gió mơn man thổi thoa nhẹ lên tấm thân mỏi mệt của chú bé. Cậu bé bắt đầu thấy buồn ngủ. Vừa đặt mình xuống cậu bé bỗng ngước mắt nhìn lên những cành cây. Bấy giờ cậu bé bỗng thấy mình thật kiêu hãnh, cậu vẫn thường hay khoe với mọi người rằng cậu có tài chăn cừu và đàn cừu của cậu nhờ vậy mà lớn rất nhanh. Khi cậu bé phát hiện ra cây đa chỉ có những chùm quả rất nhỏ, nó bắt đầu thấy ngạc nhiên. Cậu bắt đầu chế giễu: hư, một cái cây to khỏe thế này mà làm sao chỉ có những bông hoa những chùm quả bé tí tẹo thế kia, mọi người vẫn bảo là cái cây này thông thái lắm kia mà nhưng làm sao nó có thể thông thái khi mà quả của nó chỉ toàn bé xíu như vậy. Dĩ nhiên là cây đa nghe hết những lời của cậu bé nhưng cây vẫn im lặng và cành lá chỉ khẽ rung rinh đủ để cho gió cất lên khúc hát ru êm dịu. Cậu bé bắt đầu ngủ, cậu ngáy o o…. Cốc.\n" +
            "Quả đa nhỏ rụng chính giữa trán của cậu bé, nó bừng tỉnh nhưng càu nhàu: “Gừm… người ta vừa mới chợp mắt được có một tí”, rồi nó nhặt quả đa lên chưa hết chưa biết định làm gì với quả đa này bỗng nhiên cậu bé nghe thấy có tiếng cười khúc khích, cậu nghe thấy cây hỏi:\n" +
            "– Có đau không ?.\n" +
            "– Không nhưng mà làm người ta mất cả giấc ngủ .\n" +
            "– Đó là bài học cho cậu bé to đầu đấy. Cậu chẳng vừa nhạo tôi là chỉ sinh ra toàn những quả nhỏ xíu là gì.\n" +
            "– Tôi nhạo đấy tại sao người đời lại bảo bác là thông thái được nhỉ? Phá giấc ngủ trưa của người khác! Thế cũng là thông minh chắc!.\n" +
            "Cây cười và nói: này này anh bạn anh hãy nghe đây những chiếc lá của tôi cho cậu bóng mát để cậu lấy chỗ nghỉ ngơi. Ừ thì cứ cho là quả của tôi nó bé đi chăng nữa nhưng chẳng lẽ cậu không thấy rằng tạo hóa hoạt động rất hoàn chỉnh đó sao. Cậu thử tưởng tượng xem, nếu quả của tôi to như quả dừa thì điều gì sẽ xảy ra khi nó rơi vào đầu cậu.\n" +
            "Cậu bé im thin thít: ừ nhở. Cậu chưa hề nghĩ đến điều này bao giờ cả.\n" +
            "Cây lại nhẹ nhàng tiếp lời:\n" +
            "– Những người khiêm tốn có thể học hỏi rất nhiều điều từ việc quan sát những vật xung quanh đấy cậu bé ạ.\n" +
            "– Vâng bác đa bác cứ nói tiếp đi.\n" +
            "– Cậu hãy bắt đầu làm bạn với những gì ở quanh cậu. Chúng ta tất cả đều cần tới nhau. Cậu cứ nhìn bầy ong kia mà xem. Nhờ có ong mà hoa của tôi mới có thể trở thành quả. Thế còn bầy chim kia thì sao. Chúng làm tổ ngay giữa tán lá của tôi đây này. Những con chim bố mẹ kia phải làm việc vất vả cả ngày để bắt sâu nuôi con và cậu có biết việc làm đó có ý nghĩa gì với tôi không?.\n" +
            "– Không, có ý nghĩa gì vậy hả bác?.\n" +
            "– Sâu ăn lá chính vậy loài chim kia chính là những người bạn của tôi. Chúng còn giúp cả cậu nữa đấy, sở dĩ cừu của cậu có đủ lá và cỏ để ăn là vì chim chóc đã tiêu diệt hết các loài côn trùng và sâu bọ. Và chưa hết đâu cậu bé ạ!.\n" +
            "– Còn gì thế nữa hả bác đa.\n" +
            "– Cậu hãy nhìn xuống chân mình mà xem, những chiếc lá rụng tạo thành lớp thảm mục, những con sâu đào đất ngoi lên để ăn lá, chúng đào đất thành những lỗ nhỏ, nhờ đó không khí có thể vào được trong đất. Có không khí trong đất nên bộ rễ của tôi mới khỏe thế nào đấy. Rễ khỏe nên tôi cũng khỏe hơn. Nào thế bây giờ cậu trẻ đã hiểu chưa?.\n" +
            "– Cháu hiểu rồi thưa bác. Bác tha lỗi cho cháu nhé vì đã cười nhạo bác bác đa ạ.\n" +
            "– Không sao bây giờ cháu hãy ra dắt cừu về đi.\n" +
            "\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Có thể cậu bé chăn cừu không phải ngay sau đó sẽ trở nên khiêm tốn, học hỏi luôn được nhưng rõ ràng là cậu đã nhận ra người ta không thể sống lẻ loi được.','https://giaotrinhtiengnhat.com/wp-content/uploads/2016/07/Taisho-demokurashi.jpg',1)";
    private String SQLQuery002 = "INSERT INTO truyen VALUES (null,' NO Name','Chap 1 Kiếp này gặp lại chàng của kiếp trước thông thường, có hán tự và phiên âm Hán tự sang Hiragana giúp bạn học dễ đọc và nắm bắt nội dung \n" +
            "\n" +
            "\n" +
            "Một hôm, có một cậu bé chăn cừu ngồi nghỉ mát dưới gốc cây sau một ngày dài phơi mình dưới nắng cậu bé thấy người mệt mỏi và nóng bức. Một làn gió mơn man thổi thoa nhẹ lên tấm thân mỏi mệt của chú bé. Cậu bé bắt đầu thấy buồn ngủ. Vừa đặt mình xuống cậu bé bỗng ngước mắt nhìn lên những cành cây. Bấy giờ cậu bé bỗng thấy mình thật kiêu hãnh, cậu vẫn thường hay khoe với mọi người rằng cậu có tài chăn cừu và đàn cừu của cậu nhờ vậy mà lớn rất nhanh. Khi cậu bé phát hiện ra cây đa chỉ có những chùm quả rất nhỏ, nó bắt đầu thấy ngạc nhiên. Cậu bắt đầu chế giễu: hư, một cái cây to khỏe thế này mà làm sao chỉ có những bông hoa những chùm quả bé tí tẹo thế kia, mọi người vẫn bảo là cái cây này thông thái lắm kia mà nhưng làm sao nó có thể thông thái khi mà quả của nó chỉ toàn bé xíu như vậy. Dĩ nhiên là cây đa nghe hết những lời của cậu bé nhưng cây vẫn im lặng và cành lá chỉ khẽ rung rinh đủ để cho gió cất lên khúc hát ru êm dịu. Cậu bé bắt đầu ngủ, cậu ngáy o o…. Cốc.\n" +
            "Quả đa nhỏ rụng chính giữa trán của cậu bé, nó bừng tỉnh nhưng càu nhàu: “Gừm… người ta vừa mới chợp mắt được có một tí”, rồi nó nhặt quả đa lên chưa hết chưa biết định làm gì với quả đa này bỗng nhiên cậu bé nghe thấy có tiếng cười khúc khích, cậu nghe thấy cây hỏi:\n" +
            "– Có đau không ?.\n" +
            "– Không nhưng mà làm người ta mất cả giấc ngủ .\n" +
            "– Đó là bài học cho cậu bé to đầu đấy. Cậu chẳng vừa nhạo tôi là chỉ sinh ra toàn những quả nhỏ xíu là gì.\n" +
            "– Tôi nhạo đấy tại sao người đời lại bảo bác là thông thái được nhỉ? Phá giấc ngủ trưa của người khác! Thế cũng là thông minh chắc!.\n" +
            "Cây cười và nói: này này anh bạn anh hãy nghe đây những chiếc lá của tôi cho cậu bóng mát để cậu lấy chỗ nghỉ ngơi. Ừ thì cứ cho là quả của tôi nó bé đi chăng nữa nhưng chẳng lẽ cậu không thấy rằng tạo hóa hoạt động rất hoàn chỉnh đó sao. Cậu thử tưởng tượng xem, nếu quả của tôi to như quả dừa thì điều gì sẽ xảy ra khi nó rơi vào đầu cậu.\n" +
            "Cậu bé im thin thít: ừ nhở. Cậu chưa hề nghĩ đến điều này bao giờ cả.\n" +
            "Cây lại nhẹ nhàng tiếp lời:\n" +
            "– Những người khiêm tốn có thể học hỏi rất nhiều điều từ việc quan sát những vật xung quanh đấy cậu bé ạ.\n" +
            "– Vâng bác đa bác cứ nói tiếp đi.\n" +
            "– Cậu hãy bắt đầu làm bạn với những gì ở quanh cậu. Chúng ta tất cả đều cần tới nhau. Cậu cứ nhìn bầy ong kia mà xem. Nhờ có ong mà hoa của tôi mới có thể trở thành quả. Thế còn bầy chim kia thì sao. Chúng làm tổ ngay giữa tán lá của tôi đây này. Những con chim bố mẹ kia phải làm việc vất vả cả ngày để bắt sâu nuôi con và cậu có biết việc làm đó có ý nghĩa gì với tôi không?.\n" +
            "– Không, có ý nghĩa gì vậy hả bác?.\n" +
            "– Sâu ăn lá chính vậy loài chim kia chính là những người bạn của tôi. Chúng còn giúp cả cậu nữa đấy, sở dĩ cừu của cậu có đủ lá và cỏ để ăn là vì chim chóc đã tiêu diệt hết các loài côn trùng và sâu bọ. Và chưa hết đâu cậu bé ạ!.\n" +
            "– Còn gì thế nữa hả bác đa.\n" +
            "– Cậu hãy nhìn xuống chân mình mà xem, những chiếc lá rụng tạo thành lớp thảm mục, những con sâu đào đất ngoi lên để ăn lá, chúng đào đất thành những lỗ nhỏ, nhờ đó không khí có thể vào được trong đất. Có không khí trong đất nên bộ rễ của tôi mới khỏe thế nào đấy. Rễ khỏe nên tôi cũng khỏe hơn. Nào thế bây giờ cậu trẻ đã hiểu chưa?.\n" +
            "– Cháu hiểu rồi thưa bác. Bác tha lỗi cho cháu nhé vì đã cười nhạo bác bác đa ạ.\n" +
            "– Không sao bây giờ cháu hãy ra dắt cừu về đi.\n" +
            "\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Có thể cậu bé chăn cừu không phải ngay sau đó sẽ trở nên khiêm tốn, học hỏi luôn được nhưng rõ ràng là cậu đã nhận ra người ta không thể sống lẻ loi được.','https://www.wikihow.vn/%C4%90%E1%BB%8Dc-truy%E1%BB%87n-tranh-Nh%E1%BA%ADt-b%E1%BA%A3n-%28manga%29#/T%E1%BA%ADp_tin:Read-Manga-Step-4.jpg',1)";
    private String SQLQuery003 = "INSERT INTO truyen VALUES (null,'Ngủ Chung Đâu Cần Cởi Đồ','Sugiura Tomoko, cô nàng xinh đẹp và có năng lực, luôn nỗ lực hết mình để đạt thành công trong sự nghiệp \n" +
            "\n" +
            "\n" +
            "Một hôm, có một cậu bé chăn cừu ngồi nghỉ mát dưới gốc cây sau một ngày dài phơi mình dưới nắng cậu bé thấy người mệt mỏi và nóng bức. Một làn gió mơn man thổi thoa nhẹ lên tấm thân mỏi mệt của chú bé. Cậu bé bắt đầu thấy buồn ngủ. Vừa đặt mình xuống cậu bé bỗng ngước mắt nhìn lên những cành cây. Bấy giờ cậu bé bỗng thấy mình thật kiêu hãnh, cậu vẫn thường hay khoe với mọi người rằng cậu có tài chăn cừu và đàn cừu của cậu nhờ vậy mà lớn rất nhanh. Khi cậu bé phát hiện ra cây đa chỉ có những chùm quả rất nhỏ, nó bắt đầu thấy ngạc nhiên. Cậu bắt đầu chế giễu: hư, một cái cây to khỏe thế này mà làm sao chỉ có những bông hoa những chùm quả bé tí tẹo thế kia, mọi người vẫn bảo là cái cây này thông thái lắm kia mà nhưng làm sao nó có thể thông thái khi mà quả của nó chỉ toàn bé xíu như vậy. Dĩ nhiên là cây đa nghe hết những lời của cậu bé nhưng cây vẫn im lặng và cành lá chỉ khẽ rung rinh đủ để cho gió cất lên khúc hát ru êm dịu. Cậu bé bắt đầu ngủ, cậu ngáy o o…. Cốc.\n" +
            "Quả đa nhỏ rụng chính giữa trán của cậu bé, nó bừng tỉnh nhưng càu nhàu: “Gừm… người ta vừa mới chợp mắt được có một tí”, rồi nó nhặt quả đa lên chưa hết chưa biết định làm gì với quả đa này bỗng nhiên cậu bé nghe thấy có tiếng cười khúc khích, cậu nghe thấy cây hỏi:\n" +
            "– Có đau không ?.\n" +
            "– Không nhưng mà làm người ta mất cả giấc ngủ .\n" +
            "– Đó là bài học cho cậu bé to đầu đấy. Cậu chẳng vừa nhạo tôi là chỉ sinh ra toàn những quả nhỏ xíu là gì.\n" +
            "– Tôi nhạo đấy tại sao người đời lại bảo bác là thông thái được nhỉ? Phá giấc ngủ trưa của người khác! Thế cũng là thông minh chắc!.\n" +
            "Cây cười và nói: này này anh bạn anh hãy nghe đây những chiếc lá của tôi cho cậu bóng mát để cậu lấy chỗ nghỉ ngơi. Ừ thì cứ cho là quả của tôi nó bé đi chăng nữa nhưng chẳng lẽ cậu không thấy rằng tạo hóa hoạt động rất hoàn chỉnh đó sao. Cậu thử tưởng tượng xem, nếu quả của tôi to như quả dừa thì điều gì sẽ xảy ra khi nó rơi vào đầu cậu.\n" +
            "Cậu bé im thin thít: ừ nhở. Cậu chưa hề nghĩ đến điều này bao giờ cả.\n" +
            "Cây lại nhẹ nhàng tiếp lời:\n" +
            "– Những người khiêm tốn có thể học hỏi rất nhiều điều từ việc quan sát những vật xung quanh đấy cậu bé ạ.\n" +
            "– Vâng bác đa bác cứ nói tiếp đi.\n" +
            "– Cậu hãy bắt đầu làm bạn với những gì ở quanh cậu. Chúng ta tất cả đều cần tới nhau. Cậu cứ nhìn bầy ong kia mà xem. Nhờ có ong mà hoa của tôi mới có thể trở thành quả. Thế còn bầy chim kia thì sao. Chúng làm tổ ngay giữa tán lá của tôi đây này. Những con chim bố mẹ kia phải làm việc vất vả cả ngày để bắt sâu nuôi con và cậu có biết việc làm đó có ý nghĩa gì với tôi không?.\n" +
            "– Không, có ý nghĩa gì vậy hả bác?.\n" +
            "– Sâu ăn lá chính vậy loài chim kia chính là những người bạn của tôi. Chúng còn giúp cả cậu nữa đấy, sở dĩ cừu của cậu có đủ lá và cỏ để ăn là vì chim chóc đã tiêu diệt hết các loài côn trùng và sâu bọ. Và chưa hết đâu cậu bé ạ!.\n" +
            "– Còn gì thế nữa hả bác đa.\n" +
            "– Cậu hãy nhìn xuống chân mình mà xem, những chiếc lá rụng tạo thành lớp thảm mục, những con sâu đào đất ngoi lên để ăn lá, chúng đào đất thành những lỗ nhỏ, nhờ đó không khí có thể vào được trong đất. Có không khí trong đất nên bộ rễ của tôi mới khỏe thế nào đấy. Rễ khỏe nên tôi cũng khỏe hơn. Nào thế bây giờ cậu trẻ đã hiểu chưa?.\n" +
            "– Cháu hiểu rồi thưa bác. Bác tha lỗi cho cháu nhé vì đã cười nhạo bác bác đa ạ.\n" +
            "– Không sao bây giờ cháu hãy ra dắt cừu về đi.\n" +
            "\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Có thể cậu bé chăn cừu không phải ngay sau đó sẽ trở nên khiêm tốn, học hỏi luôn được nhưng rõ ràng là cậu đã nhận ra người ta không thể sống lẻ loi được.','https://www.wikihow.vn/%C4%90%E1%BB%8Dc-truy%E1%BB%87n-tranh-Nh%E1%BA%ADt-b%E1%BA%A3n-%28manga%29#/T%E1%BA%ADp_tin:Read-Manga-Step-2-Version-2.jpg',1)";
    private String SQLQuery004 = "INSERT INTO truyen VALUES (null,'Cưng Chiều Vợ Yêu','Phòng lửa, phòng trộm, phòng bạn thân. Vậy mà Lịch Tiêm Lạc lại gặp phải chuyện máu chó này. Lấy chồng ngoài ý muốn, chồng lại là một cực phẩm sủng thê cuồng, tổng tài phu nhân tiến giai công lược, dạy em phục thù bạn trai cũ, vả mặt bạn thân\n" +
            "\n" +
            "\n" +
            "Một hôm, có một cậu bé chăn cừu ngồi nghỉ mát dưới gốc cây sau một ngày dài phơi mình dưới nắng cậu bé thấy người mệt mỏi và nóng bức. Một làn gió mơn man thổi thoa nhẹ lên tấm thân mỏi mệt của chú bé. Cậu bé bắt đầu thấy buồn ngủ. Vừa đặt mình xuống cậu bé bỗng ngước mắt nhìn lên những cành cây. Bấy giờ cậu bé bỗng thấy mình thật kiêu hãnh, cậu vẫn thường hay khoe với mọi người rằng cậu có tài chăn cừu và đàn cừu của cậu nhờ vậy mà lớn rất nhanh. Khi cậu bé phát hiện ra cây đa chỉ có những chùm quả rất nhỏ, nó bắt đầu thấy ngạc nhiên. Cậu bắt đầu chế giễu: hư, một cái cây to khỏe thế này mà làm sao chỉ có những bông hoa những chùm quả bé tí tẹo thế kia, mọi người vẫn bảo là cái cây này thông thái lắm kia mà nhưng làm sao nó có thể thông thái khi mà quả của nó chỉ toàn bé xíu như vậy. Dĩ nhiên là cây đa nghe hết những lời của cậu bé nhưng cây vẫn im lặng và cành lá chỉ khẽ rung rinh đủ để cho gió cất lên khúc hát ru êm dịu. Cậu bé bắt đầu ngủ, cậu ngáy o o…. Cốc.\n" +
            "Quả đa nhỏ rụng chính giữa trán của cậu bé, nó bừng tỉnh nhưng càu nhàu: “Gừm… người ta vừa mới chợp mắt được có một tí”, rồi nó nhặt quả đa lên chưa hết chưa biết định làm gì với quả đa này bỗng nhiên cậu bé nghe thấy có tiếng cười khúc khích, cậu nghe thấy cây hỏi:\n" +
            "– Có đau không ?.\n" +
            "– Không nhưng mà làm người ta mất cả giấc ngủ .\n" +
            "– Đó là bài học cho cậu bé to đầu đấy. Cậu chẳng vừa nhạo tôi là chỉ sinh ra toàn những quả nhỏ xíu là gì.\n" +
            "– Tôi nhạo đấy tại sao người đời lại bảo bác là thông thái được nhỉ? Phá giấc ngủ trưa của người khác! Thế cũng là thông minh chắc!.\n" +
            "Cây cười và nói: này này anh bạn anh hãy nghe đây những chiếc lá của tôi cho cậu bóng mát để cậu lấy chỗ nghỉ ngơi. Ừ thì cứ cho là quả của tôi nó bé đi chăng nữa nhưng chẳng lẽ cậu không thấy rằng tạo hóa hoạt động rất hoàn chỉnh đó sao. Cậu thử tưởng tượng xem, nếu quả của tôi to như quả dừa thì điều gì sẽ xảy ra khi nó rơi vào đầu cậu.\n" +
            "Cậu bé im thin thít: ừ nhở. Cậu chưa hề nghĩ đến điều này bao giờ cả.\n" +
            "Cây lại nhẹ nhàng tiếp lời:\n" +
            "– Những người khiêm tốn có thể học hỏi rất nhiều điều từ việc quan sát những vật xung quanh đấy cậu bé ạ.\n" +
            "– Vâng bác đa bác cứ nói tiếp đi.\n" +
            "– Cậu hãy bắt đầu làm bạn với những gì ở quanh cậu. Chúng ta tất cả đều cần tới nhau. Cậu cứ nhìn bầy ong kia mà xem. Nhờ có ong mà hoa của tôi mới có thể trở thành quả. Thế còn bầy chim kia thì sao. Chúng làm tổ ngay giữa tán lá của tôi đây này. Những con chim bố mẹ kia phải làm việc vất vả cả ngày để bắt sâu nuôi con và cậu có biết việc làm đó có ý nghĩa gì với tôi không?.\n" +
            "– Không, có ý nghĩa gì vậy hả bác?.\n" +
            "– Sâu ăn lá chính vậy loài chim kia chính là những người bạn của tôi. Chúng còn giúp cả cậu nữa đấy, sở dĩ cừu của cậu có đủ lá và cỏ để ăn là vì chim chóc đã tiêu diệt hết các loài côn trùng và sâu bọ. Và chưa hết đâu cậu bé ạ!.\n" +
            "– Còn gì thế nữa hả bác đa.\n" +
            "– Cậu hãy nhìn xuống chân mình mà xem, những chiếc lá rụng tạo thành lớp thảm mục, những con sâu đào đất ngoi lên để ăn lá, chúng đào đất thành những lỗ nhỏ, nhờ đó không khí có thể vào được trong đất. Có không khí trong đất nên bộ rễ của tôi mới khỏe thế nào đấy. Rễ khỏe nên tôi cũng khỏe hơn. Nào thế bây giờ cậu trẻ đã hiểu chưa?.\n" +
            "– Cháu hiểu rồi thưa bác. Bác tha lỗi cho cháu nhé vì đã cười nhạo bác bác đa ạ.\n" +
            "– Không sao bây giờ cháu hãy ra dắt cừu về đi.\n" +
            "\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Có thể cậu bé chăn cừu không phải ngay sau đó sẽ trở nên khiêm tốn, học hỏi luôn được nhưng rõ ràng là cậu đã nhận ra người ta không thể sống lẻ loi được.','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQiYgkuICYrSAdmW4NAqun8BBHohAnznwQEi4MO5pQp6hKtM4pNMzzZPdm63qCpm20MfsM&usqp=CAU',1)";
    //Tạo bảng tại phương thức này
    public databasedoctruyen(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
        db.execSQL(SQLQuery1);
        db.execSQL(SQLQuery100);
        db.execSQL(SQLQuery2);
        db.execSQL(SQLQuery3);
        db.execSQL(SQLQuery4);
        db.execSQL(SQLQuery5);
        db.execSQL(SQLQuery6);
        db.execSQL(SQLQuery7);
        db.execSQL(SQLQuery8);
        db.execSQL(SQLQuery9);
        db.execSQL(SQLQuery10);
        db.execSQL(SQLQuery11);
        db.execSQL(SQLQuery12);
        db.execSQL(SQLQuery001);
        db.execSQL(SQLQuery002);
        db.execSQL(SQLQuery003);
        db.execSQL(SQLQuery004);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //Lấy tất cả tài khoản
    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_TAIKHOAN , null );
        return res;
    }


    //Phương thức thêm tài khoản
    public void AddTaiKhoan(TaiKhoan taiKhoan){
        SQLiteDatabase db = this.getWritableDatabase();

        //không thể lưu trực tiếp xuống insert nên thông qua contentvalues
        ContentValues values = new ContentValues();
        values.put(TEN_TAI_KHOAN,taiKhoan.getmTenTaiKhoan());
        values.put(MAT_KHAU,taiKhoan.getmMatKhau());
        values.put(EMAIL,taiKhoan.getmEmail());
        values.put(PHAN_QUYEN,taiKhoan.getmPhanQuyen());

        db.insert(TABLE_TAIKHOAN,null,values);
        //đóng lại db cho an toàn
        db.close();
        //Log.e("Add Tai Khoan ","thành công");
    }

    //Lấy 7 truyện mới nhất
    public Cursor getData1(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res =  db.rawQuery( "select * from "+TABLE_TRUYEN+" ORDER BY "+ID_TRUYEN+" DESC LIMIT 7" , null );
        return res;
    }

    //Lấy tất cả truyện
    public Cursor getData2(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_TRUYEN,null);
        return res;
    }


    //Hàm thêm và xóa truyện database
    //Add truyện
    public void AddTruyen(Truyen truyen){

        //Cập nhật dữ liệu
        SQLiteDatabase db = this.getWritableDatabase();

        //không thể lưu trực tiếp xuống insert nên thông qua contentvalues
        ContentValues values = new ContentValues();
        values.put(TEN_TRUYEN, truyen.getTenTruyen()); // Lấy tên truyện
        values.put(NOI_DUNG, truyen.getNoiDung()); // Lấy nội dung
        values.put(IMAGE, truyen.getAnh()); //Lấy ảnh
        values.put(ID_TAI_KHOAN, truyen.getID_TK());// Lấy id tài khoản

        db.insert(TABLE_TRUYEN, null, values);
        db.close();
    }


    //Lấy chapter
    public ArrayList<Chapter> getAllChapter(int id){

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Chapter> array = new ArrayList<>();

        Cursor res =  db.rawQuery( "select * from "+TABLE_CHAPTER+" WHERE "+ID_TRUYEN+" = ?" , new String[]{String.valueOf(id)} );
        while (res.moveToNext()){

            //List truyện gồm 0.id, 1.tentruyen, 2.noidung, 3.anh, 4.id_tk.
            int idchapter = res.getInt(0);
            int idtruyen = res.getInt(1);
            String url = res.getString(2);
            String tentruyen = res.getString(3);

            //Add nội dung vào list truyện
            array.add(new Chapter(idchapter, idtruyen, tentruyen, url));//run

        }

        return array;
    }

    public Chapter getChapter(int idchapter) {
        SQLiteDatabase db = this.getReadableDatabase()  ;
        Chapter chapter = null;

        Cursor res =  db.rawQuery( "select * from "+TABLE_CHAPTER+" WHERE "+ID_CHAPTER+" = ?" , new String[]{String.valueOf(idchapter)} );
        while (res.moveToNext()){

            int idtruyen = res.getInt(1);
            String url = res.getString(2);
            String tentruyen = res.getString(3);

            //Add nội dung vào list truyện
            chapter = new Chapter(idchapter, idtruyen, tentruyen, url);//run

        }
        return chapter;
    }


    //Add chapter
    public void addChapter(Chapter chapter){

        //Cập nhật dữ liệu
        SQLiteDatabase db = this.getWritableDatabase();

        //không thể lưu trực tiếp xuống insert nên thông qua contentvalues
        ContentValues values = new ContentValues();
        values.put(ID_TRUYEN_CHAPTER, chapter.getIdTruyen());
        values.put(URL_TRUYEN, chapter.getUrl());
        values.put(TEN_CHAPTER, chapter.getNameChapter());

        db.insert(TABLE_CHAPTER, null, values);
        db.close();
    }

    //Xóa chapter
    public int deleteChapter(int id){

        SQLiteDatabase db = this.getWritableDatabase();

        int res = db.delete(TABLE_CHAPTER, ID_CHAPTER+" = ? ", new String[]{String.valueOf(id)});
        return res;
    }

    //Update chapter
    public void updateChapter (Chapter chapter){

        //Cập nhật dữ liệu
        SQLiteDatabase db = this.getWritableDatabase();

        //không thể lưu trực tiếp xuống insert nên thông qua contentvalues
        ContentValues values = new ContentValues();
        values.put(URL_TRUYEN, chapter.getUrl());
        values.put(TEN_CHAPTER, chapter.getNameChapter());

        db.update(TABLE_CHAPTER, values,ID_CHAPTER+"= ?",new String[]{String.valueOf(chapter.getId())});
        db.close();
    }

    //Xóa truyện
    public int Delete(int i){
        SQLiteDatabase db = this.getWritableDatabase();

        int res = db.delete(TABLE_TRUYEN, ID_TRUYEN+" = "+i, null);
        return res;
    }




    //Add Đánh giá
    public void AddDanhGia(DanhGia danhGia){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NOI_DUNG_DANH_GIA, danhGia.getNoiDungDanhGia());
        values.put(TEN_TAI_KHOAN, danhGia.getTenTK());
        values.put(TEN_TRUYEN, danhGia.getTenTruyen());

        db.insert(TABLE_DANH_GIA, null, values);
        db.close();
        Log.e("Add đánh giá", "Thành công");
    }


    //Lấy tất cả đánh giá
    public Cursor getData3(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_DANH_GIA,null);
        return res;
    }


}
