package com.example.springcommerce;

import com.example.springcommerce.entity.Product;
import com.example.springcommerce.entity.Role;
import com.example.springcommerce.entity.User;
import com.example.springcommerce.model.UserModel;
import com.example.springcommerce.repository.ProductRepository;
import com.example.springcommerce.repository.RoleRepository;
import com.example.springcommerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.springcommerce.service.UserService;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Arrays;


@SpringBootApplication
public class SpringcommerceApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringcommerceApplication.class, args);
    }



    @Override
    @Transactional
    public void run(String... args) {
        createAdminRole();
        createAdminUser();

//        Product product1  = new Product(1L,"Historic Manhattan",150000,"Historic Manhattan là một loại cocktail mang đậm dấu ấn lịch sử, tôn vinh hương vị cổ điển của Manhattan, một đồ uống có nguồn gốc từ thế kỷ 19. Thông thường, nó được chế biến với whisky loại rye, vermouth ngọt và các loại gia vị đắng, và có thể được trang trí bằng quả anh đào maraschino để tăng thêm sự độc đáo và ngọt ngào. Được tạo ra với sự tinh tế, cocktail này là biểu tượng của văn hóa cocktail bền vững, mang lại sự hòa quyện mượt mà và cân đối giữa các loại rượu, ghi chép lại bản chất của một thời kỳ đã qua. Cho dù thưởng thức tại một quán bar mang dáng vẻ của thời đại cũ hay một không gian hiện đại với sự tôn trọng đối với truyền thống, Historic Manhattan đứng làm biểu tượng của gu thưởng thức lâu dài và sự tinh tế.",57,"/Manhattan.jpg");
//        Product product2 = new Product(2L,"Brandy Alexander",160000,"Brandy Alexander là một loại cocktail tinh tế và ngon miệng, được chế biến từ brandy, creme de cacao (một loại nước mắm cacao), và kem. Đôi khi được trang trí bằng một lớp bột quế nhẹ hoặc hòa quyện với hạt cacao nổi bật trên mặt cốc, Brandy Alexander mang đến hương vị ngọt ngào, thơm ngon và độ cũ kỹ đặc trưng. Đây là một đồ uống thích hợp cho những buổi tối dễ chịu, nơi bạn có thể thư giãn và thưởng thức hương vị độc đáo của brandy, kết hợp với vị ngọt và béo của creme de cacao và kem. Brandy Alexander là một biểu tượng của sự sang trọng và êm dịu trong thế giới của cocktail." , 25,"/brandy_alexander.jpg");
//        Product product3 = new Product(3L,"Clyde Common Whiskey Sour",170000,"Clyde Common Whiskey Sour là một phiên bản tinh tế và đặc biệt của cocktail Whiskey Sour tại quán Clyde Common ở Portland, Oregon. Thức uống này kết hợp hài hòa giữa hương vị độc đáo của whiskey, chua chát từ nước cốt chanh tươi, và sự ngọt ngào từ đường đơn. Có thể có thêm trân châu trắng từ trứng để tạo nên độ béo và sánh mịn cho cocktail. Được phục vụ trên đá trong một cốc, Clyde Common Whiskey Sour là sự kết hợp hoàn hảo của các thành phần, tạo ra một đồ uống thanh lịch, hấp dẫn và đậm đà. Đây là lựa chọn lý tưởng cho những người muốn thưởng thức một phiên bản đặc biệt của Whiskey Sour tại điểm đến nổi tiếng này.",58,"/ClydeCommonWhiskeySour.jpg");
//        Product product4 = new Product(4L,"Tom Collins",80000,"Tom Collins là một lựa chọn cocktail truyền thống và sảng khoái. Với cơ sở là gin, đồ uống này được nâng cao bởi hương chua của nước cốt chanh tươi, một chút ngọt từ đường đơn, và được phục vụ với nước suối tạo cảm giác sôi động. Được phục vụ trên đá trong một cốc cao, nó hiện thực hóa sự kết hợp hoàn hảo giữa sự tươi sáng của chanh và độ sâu của các thành phần thảo mộc. Tom Collins tại Clyde Common là một sự chọn lựa thanh lịch và thời thượng cho những người muốn thưởng thức một đồ uống nhẹ và làm mới tại điểm đến nổi tiếng này ở Portland.",35,"/tomcollins.jpg");
//        Product product5 = new Product(5L,"Martini",85000,"Martini, một trong những biểu tượng của thế giới cocktail, tỏa sáng với sự đơn giản và đẳng cấp. Cùng với hương thơm nồng nàn của gin và vị thanh lịch của vermouth, Martini không chỉ là một thức uống, mà còn là một trải nghiệm. Khi được phục vụ trong cốc Martini chills, đôi khi cả tạt tuyết, chiếc cốc này là bảo tàng di động của sự tinh tế và phong cách. Cho dù bạn ưa thích gin hoặc vodka, và liệu bạn chọn garnish là olive hay vỏ cam, Martini là biểu tượng của sự đẳng cấp và tinh tế, là một hành trình qua thời gian trong thế giới rộng lớn của cocktail.",39,"/martini.jpg");
//        Product product6 = new Product(6L,"Bloody Mary",90000,"Bloody Mary là một cocktail kinh điển với hương vị mạnh mẽ, thường thức vào các buổi brunch hoặc ban ngày. Được tạo nên từ sự hòa quện của vodka, nước cà chua, và nước cốt chanh, nó trở nên độc đáo với chút sốt Worcestershire và sốt cay Tabasco, tạo nên lớp hương vị cay nồng. Có thể thêm muối và tiêu để làm nổi bật hương vị, và một chút muối cần cây có thể tạo điểm nhấn cho mép cốc. Thưởng thức ly Bloody Mary với đá và trang trí theo sở thích cá nhân như cây cần cây, quả lê, hay oliu là một trải nghiệm cocktail thú vị và hấp dẫn.",24,"/bloodymary.jpg");
//        Product product7 = new Product(7L,"Mojito",180000,"Mojito, một trong những cocktail tinh tế và sảng khoái nhất, quyến rũ với hương vị tươi mới và dễ uống. Nhấn mạnh với hương thơm của rum trắng, lá bạc hà tươi, và đường đơn, Mojito là sự pha trộn tinh tế giữa ngọt, chua, và cay nồng. Làm mới bởi nước cốt chanh tươi và nước soda nhẹ nhàng, đồ uống này trở nên hoàn hảo khi thưởng thức dưới ánh nắng mặt trời. Với đá viên mát lạnh và trang trí đơn giản của lát chanh và lá bạc hà, Mojito là biểu tượng của sự thư giãn và hương vị tinh tế trong thế giới cocktail.",15,"/mojito.jpg");
//        Product product8 = new Product(8L,"Dark n’ Stormy",190000,"Dark 'n' Stormy, một biểu tượng của hương vị độc đáo và sức mạnh trong thế giới cocktail. Nguồn gốc từ Bermuda, đồ uống này đơn giản nhưng mạnh mẽ, kết hợp vị ngọt của rum đen với sự cay nồng đặc trưng từ nước đào đen (ginger beer). Khi rót nhanh chóng lên trên lớp đen của rum, nó tạo ra một hình ảnh đẹp như cơn bão đang đổ bộ, đúng với tên gọi của mình. Ăn ý và mát mẻ với đá, Dark 'n' Stormy là sự hòa quện hoàn hảo giữa sức mạnh và hương vị, làm cho nó trở thành lựa chọn lý tưởng cho những khoảnh khắc giải trí và thư giãn.",17,"/cocktail_dark_and_stormy.jpg");
//        Product product9 = new Product(9L,"Painkiller",175000,"Painkiller, một đóa hoa giữa thế giới cocktail, mang đến sự trải nghiệm ngọt ngào và dễ chịu. Với hương vị độc đáo từ rum Dunkin' hay dark rum, sự mềm mại từ nước cốt dừa cream và tinh tế của nước cốt dừa, Painkiller là một hành trình ngắn đến kỳ nghỉ trên bãi biển. Hòa quyện với nước cốt cam và đá, đồ uống này không chỉ mát lạnh mà còn hứa hẹn một cảm giác nhẹ nhàng nhưng sôi động. Trang trí với lát cam và cây thì là, mỗi giọt Painkiller là một cái nhấn của sự thoải mái và hạnh phúc. Một lựa chọn tuyệt vời cho những lúc cần tìm lại bình yên và sự thư giãn.",23,"/pankiller.jpg");
//        Product product10= new Product(10L,"Rattlesnake",200000,"Rattlesnake, một tác phẩm nghệ thuật cocktail độc đáo, biểu tượng cho sự hòa quện của bourbon hoặc rye whiskey với nước cốt chanh tươi, đường đơn và một chút trứng trắng, tạo ra một hương vị độc đáo và phức tạp. Đây không chỉ là một đồ uống mạnh mẽ, mà còn là một trải nghiệm độc đáo với sự sánh mịn từ trứng trắng. Cho dù được thưởng thức trong không khí ấm cúng của một quán bar sang trọng hay tại nhà trong bầu không khí riêng tư, mỗi giọt Rattlesnake là một cuộc phiêu lưu qua hương vị và tinh tế của thế giới cocktail.",28,"/Rattlesnake.jpg");
//        Product product11= new Product(11L,"Mint Julep",95000,"Mint Julep, một trong những biểu tượng của thế giới cocktail, là một đồ uống mát lạnh và thơm ngon. Thường được phục vụ trong cốc bạc hà để giữ cho đồ uống lạnh mát, Mint Julep kết hợp hương vị độc đáo của bourbon với sự tươi mới và mát lạnh từ lá bạc hà. Các lá bạc hà được nhẹ nhàng nghiền để khuếch tán hương thơm, tạo nên một hương vị nhẹ nhàng và phức tạp. Thưởng thức Mint Julep là một hành trình đến thế giới của sự sang trọng và êm dịu, đặc biệt phổ biến tại các sự kiện đua ngựa như Kentucky Derby.",25,"/classic-mint-julep.jpg");
//        Product product12= new Product(12L,"Sazerac",145000,"Sazerac, một tác phẩm nghệ thuật cocktail độc đáo, đưa bạn trở lại thời kỳ cổ điển của New Orleans. Bourbon hoặc rye whiskey, với đường đơn và một chút absinthe, tạo nên một hương vị phức tạp và đặc trưng. Khi rót nhẹ lên trên lớp bùn absinthe, mỗi giọt Sazerac là một chuyến phiêu lưu qua thời gian, nơi mà hương vị của quá khứ gặp gỡ với đẳng cấp hiện đại. Được phục vụ trong cốc lạnh, Sazerac không chỉ là một đồ uống, mà là một cảm nhận sâu sắc về văn hóa và nghệ thuật pha chế cocktail.",32,"/sazerac.jpg");
//        Product product13= new Product(13L,"Old Fashioned",92000,"Old Fashioned, với sự tinh tế và đơn giản, là một hành trình về quá khứ trong thế giới cocktail. Bourbon hoặc rye whiskey là nguồn cảm hứng chính, hòa quện với đường đơn để tạo ra một hương vị ấn tượng và mộc mạc. Mỗi giọt bitter thêm vào sự cân bằng và sâu sắc, tạo nên một trải nghiệm cocktail truyền thống nhưng vẫn đậm chất đương đại. Phục vụ trong cốc Old Fashioned và thường trang trí bằng một lớp vỏ cam, Old Fashioned không chỉ là một loại đồ uống, mà là một biểu tượng của sự lịch lãm và đẳng cấp trong nghệ thuật pha chế.",16,"/oldfashioned.png");
//        Product product14= new Product(14L,"Margarita",265000,"Margarita, biểu tượng của sự tươi mới và vị ngon, là một hành trình đến vùng đất của tequila và hương vị sôi động. Sự hòa quện giữa tequila mạnh mẽ, triple sec ngọt ngào, và nước cốt chanh tạo nên một cocktail tinh tế và sảng khoái. Phục vụ trong cốc lạnh với đường mép và đá, Margarita là một cuộc phiêu lưu qua hương vị đậm đà và đổi mới. Với hoặc không có muối trên mép cốc, Margarita không chỉ là một đồ uống, mà là biểu tượng của sự hân hoan và vui tươi trong thế giới của cocktail.",32,"/margarita.jpg");
//        Product product15= new Product(15L,"Sidecar",135000,"Sidecar, một tác phẩm nghệ thuật cocktail tinh tế và độc đáo, đưa ta trở lại thời điểm của sự lịch sử và lịch lãm. Với brandy, triple sec, và nước cốt chanh, Sidecar không chỉ là một đồ uống, mà còn là một hành trình qua hương thơm và vị ngọt chua cân bằng một cách hoàn hảo. Mỗi giọt trong cốc là một dấu ấn của sự sang trọng và sự phát triển của nghệ thuật pha chế, làm cho Sidecar trở thành biểu tượng độc đáo và không thể nào quên.",13,"/sidecar.jpg");
//        Product product16= new Product(16L,"Pisco Sour",178000,"Pisco Sour, biểu tượng của ẩm thực Nam Mỹ, là một hội tụ tinh tế của hương vị độc đáo và tươi mới. Sự hòa quện của pisco, rượu nho đặc trưng, với nước đường, nước cốt chanh tươi, và trứng trắng tạo ra một đồ uống phức tạp với hương thơm và hương vị đặc sắc. Thấu hiểu được vị ngon của Pisco Sour không chỉ là việc thưởng thức một đồ uống, mà là một hành trình khám phá qua văn hóa và nghệ thuật pha chế cocktail.",21,"/pisco-sour.jpg");
//        Product product17= new Product(17L,"Absinthe Cocktail",234000,"Absinthe Cocktail, một tác phẩm nghệ thuật cocktail độc đáo, đưa ta đến thế giới của hương thơm mạnh mẽ và trải nghiệm phức tạp. Với rượu absinthe đặc trưng, có màu xanh lục huyền bí, đôi khi kết hợp với đường đơn và nước lạnh hoặc đá, Absinthe Cocktail là một cuộc phiêu lưu qua vị ngọt đắng và cảm giác cay nồng. Cho dù được thưởng thức trực tiếp hoặc sử dụng trong các công thức pha chế sáng tạo, nó là biểu tượng của sự bí ẩn và độ nồng nàn trong thế giới cocktail.",13,"/absinthe.jpg");
//        Product product18= new Product(18L,"Negroni",250000,"Negroni, biểu tượng của sự cân bằng và đơn giản, là một tác phẩm nghệ thuật cocktail đậm đà và phức tạp. Hương thơm của gin, với vị độc đáo của vermouth đỏ và đắng cay của Campari, tạo nên một hương vị đặc trưng không thể nhầm lẫn. Phục vụ trong cốc Old Fashioned với đá và vỏ cam trang trí, Negroni không chỉ là một đồ uống, mà còn là biểu tượng của sự thanh lịch và đẳng cấp trong thế giới cocktail.",12,"/negroni.jpg");
//        Product product19= new Product(19L,"Bramble",250000,"Bramble, một tuyệt phẩm của thế giới cocktail, là một hành trình qua hương vị tươi mới và độ ngon miệng. Với gin mang đến hương thơm của nho và nước cốt chanh tươi mát, Bramble trở nên ngọt ngào và mê đắm nhờ vào mâm xôi, tạo nên một biểu tượng của sự cân bằng hài hòa. Khi được phục vụ trong cốc Old Fashioned với đá, Bramble không chỉ là một đồ uống, mà là một trải nghiệm thưởng thức tinh tế và phức tạp.",20,"/bramble.jpg");
//        Product product20= new Product(20L,"Southside",260000,"Southside, một biểu tượng của sự tươi mới và hương vị dễ uống, là một hành trình qua thời kỳ Cấm với hương thơm nho từ gin, vị ngọt của đường đơn, và sự mát lạnh của nước cốt chanh. Lá bạc hà, như một chi tiết tinh tế, tạo nên một lớp hương thơm tươi mới, khiến mỗi giọt Southside trở nên dễ chịu và gần gũi như một cơn gió mùa hè. Phục vụ trong cốc Martini hoặc cốc Old Fashioned với đá, Southside là một lựa chọn tuyệt vời cho những người yêu thích sự nhẹ nhàng và sảng khoái trong thế giới cocktail.",27,"/southside.jpg");
//        Product product21= new Product(21L,"Jungle Bird",280000,"Jungle Bird, một huyền bí đầy hương vị, là hành trình đến vùng nhiệt đới của thế giới cocktail. Với dark rum mang đến sự ngọt ngào và hương thơm độc đáo, Campari tạo nên lớp đắng cay và màu sắc nổi bật, trong khi nước cốt chanh và đường đơn thêm vào cân bằng và sự tươi mới. Phục vụ trong cốc tiki với đá, Jungle Bird không chỉ là một đồ uống, mà là một cuộc phiêu lưu đầy mê hoặc và bí ẩn trong thế giới cocktail.",5,"/jungle-bird.jpg");
//
//
//        productRepository.save(product1);
//        productRepository.save(product2);
//        productRepository.save(product3);
//        productRepository.save(product4);
//        productRepository.save(product5);
//        productRepository.save(product6);
//        productRepository.save(product7);
//        productRepository.save(product8);
//        productRepository.save(product9);
//        productRepository.save(product10);
//        productRepository.save(product11);
//        productRepository.save(product12);
//        productRepository.save(product13);
//        productRepository.save(product14);
//        productRepository.save(product15);
//        productRepository.save(product16);
//        productRepository.save(product17);
//        productRepository.save(product18);
//        productRepository.save(product19);
//        productRepository.save(product20);
//        productRepository.save(product21);
    }

    private void createAdminUser() {
        if (userRepository.findByUsername("admin") == null) {
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setEmail("admin@moonlightcoctail.com");
            adminUser.setPassword(passwordEncoder.encode("abc"));
            Role adminRole = roleRepository.findByName("ADMIN");
            adminUser.setRoles(Arrays.asList(adminRole));
            this.userService.save(adminUser);
        }
    }

    private void createAdminRole() {
        Role adminRole = roleRepository.findByName("ADMIN");
        if (adminRole == null) {
            adminRole = new Role();
            adminRole.setName("ADMIN");
            roleRepository.save(adminRole);
        }
    }

    private void createUserRole() {
        Role userRole = roleRepository.findByName("USER");
        if (userRole == null) {
            userRole = new Role();
            userRole.setName("USER");
            roleRepository.save(userRole);
        }
    }




}
