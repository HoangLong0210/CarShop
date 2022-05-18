create database CAR

use CAR

create table AD(
	id int primary key identity(1,1),
	username varchar(50),
	pass varchar(50) not null,
	email varchar(100) not null,
)

create table Classs(
	id int primary key identity(1,1),
	name nvarchar(50) not null unique,
	disc nvarchar(max) not null,
	img varchar(200) not null,
)

create table Brands(
	id int primary key identity(1,1),
	name nvarchar(50) not null unique,
	disc nvarchar(max) not null,
	img varchar(200) not null,
)

create table Cars(
	id int primary key identity(1,1),
	img varchar(200) not null,
	name nvarchar(50) not null unique,
	video varchar(200),
	amount int default 0,
	classId int not null foreign key references Classs(id),
	disc nvarchar(max) not null,
	price numeric not null,
	brandId int not null foreign key references Brands(id)
)

create table Orders(
	id int primary key identity(1,1),
	oid AS 'OID' + RIGHT('0000000000' + CAST(ID AS VARCHAR(10)), 10) PERSISTED,
	email varchar(100) not null,
	customer nvarchar(100) not null,
	carId int not null foreign key references Cars(id),
	userId int not null foreign key references AD(id), 
	phone nvarchar(15) not null,
	addres nvarchar(100) not null,
	datebuy date not null,
	amount int not null,
	total numeric not null,
	stat int not null,
)

create table Securities(
	token varchar(100) primary key,
	expired varchar(15) not null,
	created date not null,
	id int not null,
	type varchar(1) not null
)

insert into AD values 
('sa', '123', 'testmail24681357@gmail.com'),
('tiennhot', '123', 'testmail24681357@gmail.com'),
('sai', '123', 'testmail24681357@gmail.com')

insert into Classs values
('Sport', N'Xe thể thao là một chiếc ô tô được thiết kế để nhấn mạnh việc xử lý, hiệu suất hoặc sự phấn khích khi lái xe. Xe thể thao có nguồn gốc từ châu Âu vào đầu những năm 1900 và hiện đang được nhiều nhà sản xuất ô tô trên thế giới sản xuất.', N'resources/image/type/sport.png'),
('Super', N'Siêu xe là từ bắt đầu thường dùng vào thập niên 1980 để gọi các chiếc xe thể thao đắt tiền, được thiết kế chủ yếu chú trọng vào tốc độ. ', N'resources/image/type/super.png'),
('Race', N'Đua xe hơi hay Đua ô tô là môn thể thao tốc độ thi đấu bằng kỹ thuật điều khiển ô tô trên đường đua. Đường đua ô tô có thể là đường giao thông bình thường, đường chuyên dùng trong các trường đua hoặc những đường địa hình. Thành tích đua ô tô thường được tính bằng thời gian.', N'resources/image/type/race.png'),
('Concept', N'Một chiếc xe ý tưởng là một chiếc xe được sản xuất để thể hiện phong cách mới và / hoặc công nghệ mới. Chúng thường được trưng bày tại các triển lãm xe máy để đánh giá phản ứng của khách hàng đối với các thiết kế mới và triệt để có thể được sản xuất hàng loạt.', N'resources/image/type/concept.png'),
('Muscle', N'Xe cơ bắp là từ dùng để chỉ một chiếc xe hiệu suất cao của Mỹ, theo một số định nghĩa là một chiếc xe cỡ trung bình được trang bị động cơ V8 phân khối lớn. Về mặt lịch sử, chúng đều là hệ dẫn động cầu sau, nhưng điều đó đã thay đổi với những tiến bộ công nghệ.', N'resources/image/type/muscle.png'),
('Luxury', N'Xe sang trọng hay xe hơi hạng sang là một chiếc xe có mục đích cung cấp cho hành khách sự thoải mái, mức độ trang bị cao cấp hơn và chất lượng đẳng cấp hơn so với xe thông thường với giá cả đắt hơn xe gốc.', N'resources/image/type/luxury.png'),
('Offroad', N'Phương tiện địa hình đôi khi được coi là phương tiện vượt địa hình hoặc xe mạo hiểm được coi là bất kỳ loại phương tiện nào có khả năng lái trên và trên bề mặt lát đá hoặc sỏi. Nó thường có đặc điểm là có lốp lớn với rãnh sâu, rộng, hệ thống treo linh hoạt, hoặc thậm chí là rãnh bánh xích.', N'resources/image/type/offroad.png');

insert into Brands values
('BMW', N'BMW là một công ty sản xuất xe hơi và xe máy quan trọng của Đức. Thương hiệu ôtô của BMW nổi tiếng thế giới bởi sự sang trọng, thiết kế thể thao, khả năng vận hành cao.', N'https://giaxetot.vn/uploads/logo-bmw.png'),
('Ferrari', N'Ferrari S.p.A là một công ty sản xuất siêu xe thể thao của Ý do Enzo Ferrari sáng lập năm 1929. Với tên gọi ban đầu là Scuderia Ferrari, nhà sản xuất này chuyên tài trợ cho các tay đua và chế tạo các loại xe đua. Đến năm 1946, hãng bắt đầu sản xuất cả các mẫu xe dành cho cuộc sống hàng ngày và trở thành Ferrari S.p.A.', N'resources/image/brand/ferrari.png'),
('Ford', N'Công ty Ford Motor là một nhà sản xuất ô tô đa quốc gia , bán ô tô và xe thương mại dưới thương hiệu Ford và hầu hết các xe hạng sang dưới thương hiệu Lincoln.', N'https://giaxetot.vn/uploads/logo-ford.png'),
('Porsche', N'Porsche AG, thường được gọi tắt là Porsche, là một công ty sản xuất ô tô thể thao hạng sang của Đức, sáng lập bởi Louise Piëch và Ferdinand Porsche. Porsche có trụ sở chính đặt tại Stuttgart, Đức. Porsche từ năm 2009 là công ty ô tô con thuộc tập đoàn ô tô số một thế giới Volkswagen AG.', N'https://giaxetot.vn/uploads/logo-porsche.png'),
('Lamborghini', N'Automobili Lamborghini S.p.A., thường gọi tắt là Lamborghini, là nhà sản xuất siêu xe thể thao cao cấp của Italy, có trụ sở chính và xưởng sản xuất đặt tại Sant Agata Bolognese, gần Bologna, Italy. Hiện tại Lamborghini là công ty con thuộc tập đoàn ô tô lớn nhất thế giới Volkswagen AG của Đức.', N'https://giaxetot.vn/uploads/logo-lamborghini.png'),
('Audi', N'AUDI AG là một công ty của Đức chuyên sản xuất ô tô hạng sang dưới nhãn hiệu Audi. Công ty này là thành viên của tập đoàn ô tô lớn nhất thế giới Volkswagen AG', N'https://giaxetot.vn/uploads/Audi.png');

insert into Cars values
(N'https://danchoioto.vn/wp-content/uploads/2020/10/gia-bmw-330i.jpg', 'BMW 330i M', '', 50, 1, N'Xe BMW 330i M Sport có tất cả các đặc điểm thông thường của dòng 3 Series, nhưng được trang bị bộ bodykit mạnh mẽ hơn, thể thao hơn, bánh xe hợp kim lớn hơn và nổi bật hơn cả là viền ngoại thất bóng loáng cùng một ít huy hiệu “M Sport” xung quanh cabin', 2400, 1),
(N'https://media.ed.edmunds-media.com/bmw/i8/2019/oem/2019_bmw_i8_coupe_base_fq_oem_1_1600.jpg', 'BMW i8', '', 50, 2, N'Được ra mắt vào hồi tháng 6/2014, siêu xe thể thao hybrid BMW i8 mang kiểu dáng cực đẹp đã nhanh chóng “đốn tim“ giới đam mê tốc độ. Dù được phân phối chính thức tại hơn 50 thị trường nhưng i8 BMW hiện tại lại không có Việt Nam.', 3800, 1),
(N'https://cly.1cdn.vn/2020/11/20/lamborghini-huracan-sto_2.jpg', 'Huracan', '', 50, 1, N'Thiết kế của Lamborghini Huracan mang đến nhiều xúc cảm về sự mạnh mẽ và nguồn năng lượng bên trong. Vẫn dáng hình đặc trưng của “nhà siêu bò” nhưng hãng xe ô tô Ý đã thổi hồn của Lamborghini Huracan một chất rất riêng.', 4700, 5),
(N'https://i1-vnexpress.vnecdn.net/2020/12/17/lamborghini-sc20-squadra-corse-9205-9699-1608198430.jpg?w=300&h=180&q=100&dpr=1&fit=crop&s=Jx-f_oV3xo3bU6sUwb29wA', 'Lamborghini SC20', '', 50, 2, N'Siêu xe không mui phát triển dựa trên Aventador SVJ và trang bị động cơ 6.5 V12 hút khí tự nhiên, công suất 759 mã lực, mô-men xoắn cực đại 720 Nm. Hộp số tự động 7 cấp kết hợp dẫn động bốn bánh.', 13400, 5),
(N'https://cdn.motor1.com/images/mgl/168v9/s4/audi-rs3-lms-tcr-race-car.webp', 'Audi RS3', '', 50, 3, N'Audi RS3 2022 có diện mạo cá tính, đậm chất thể thao với mặt ca-lăng sơn màu đen kết hợp với các khe hút gió lớn hơn và lưới tản nhiệt dạng lưới tổ ong mới. Bên cạnh đó, xe cũng được trang bị cụm đèn pha LED bắt mắt hơn, chắn bùn phía trước rộng hơn, và bổ sung các khe hút gió ở vòm bánh trước. Ở phía sau, đuôi xe RS3 mới sở hữu cửa có kích thước lớn hơn, đèn hậu được tinh chỉnh, bộ khuếch tán sau chứa hệ thống ống cả RS Sport có âm thanh uy lực hơn đời cũ.', 1600, 6),
(N'https://i.pinimg.com/originals/df/d7/29/dfd7290194c552ceb0366bd7402aa71c.jpg', 'Audi R8', '', 50, 6, N'Audi R8 luôn là mẫu xe thể thao được ưa chuộng trên toàn thế giới kể từ khi trình làng. Đây cũng là mẫu xe từng sát cánh với Iron Man trong nhiều sản phẩm phim bom tấn siêu anh hùng của Marvel. Tính tới năm 2016, doanh số Audi R8 trên toàn cầu đạt gần 30.000 chiếc.', 9000, 6),
(N'https://znews-photo.zadn.vn/w660/Uploaded/obunzvu/2019_08_28/porsche988visionconceptrendering.jpg', 'Porsche 988 Vision', '', 50, 4, N'Porsche 988 Vision là mẫu siêu xe tiếp theo của Porsche, dự định ra mắt năm 2025. Cùng chiêm ngưỡng thiết kế concept mới nhất của siêu xe này. Những chiếc Porsche 918 Spyder cuối cùng đã được lắp ráp vào giữa năm 2015, kết thúc vòng đời mẫu hypercar hàng đầu của hãng xe Đức.', 8300, 4),
(N'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTRc4hM8gjDIFmPKvLyG91dqa9ngPf-a6kMMQ&usqp=CAU', 'Porsche 981 Spyder', '', 50, 2, N'The Porsche 981 is the internal designation given to the third-generation of the Boxster and second generation of the Cayman models built by German automobile manufacturer Porsche. It was announced on 13 March 2012 at the Geneva Auto Show with sales starting early summer 2012.', 7100, 4),
(N'https://xe360.com/uploads/forum/photo/us/us2/us2ox64b715b.webp', 'Ford Mustang', '', 50, 1, N'Ford Mustang là dòng xe thể thao mạnh mẽ có xuất xứ từ Mỹ, được ra mắt năm 1964. Trải qua nhiều thế hệ, Mustang trở thành xe thể thao ưa thích trên toàn thế giới bởi kiểu dáng đẹp mắt, động cơ vận hành mạnh mẽ và nhiều ưu điểm nổi bật khác.', 2900, 3),
(N'https://media.ford.com/content/fordmedia/fna/us/en/news/2019/07/04/limited-edition-ford-gt/jcr:content/image.img.881.495.jpg/1562230551502.jpg', 'Ford GT', '', 50, 3, N'Ford GT 2017 là thế hệ thứ hai của dòng xe thể thao thương mại danh tiếng thuộc Ford, bắt đầu sản xuất từ cuối 2016 và dự kiến kéo dài đến 2022. Xe được lắp ráp tại nhà máy Multimatic, thành phố Markham, bang Ontario, Canada', 8600, 3),
(N'https://i1-vnexpress.vnecdn.net/2021/06/25/Bai-Ferrari-296-GTB-1-1-1787-1624610298.jpg?w=680&h=0&q=100&dpr=1&fit=crop&s=poDZ2_mqNcEewEI_wEcOvg', 'Ferrari 296 GTB', '', 50, 6, N'Thương hiệu xe thể thao Italy giới thiệu mẫu siêu xe mới mang tên 296 GTB, bổ sung thêm lựa chọn cho khách hàng yêu thích dòng xe Ferrari. Điểm nhấn của 296 GTB nằm ở hệ truyền động hybrid, định vị nằm dưới F8 Tributo và là đối thủ của McLaren Artura.', 8600, 2)

insert into Orders values
('mark@gmail.com',	'mark',		1,	2,	'1234567890',	N'596/37 Trần Hưng Đạo',	'2019-06-10',	2,		1000,	2),
('ana@gmail.com',	'ana',		2,	3,	'1234567890',	N'596/37 Trần Hưng Đạo',	'2019-10-10',	1,		9000,	-1),
('simp@gmail.com',	'simp',		2,	2,	'1234567890',	N'596/37 Trần Hưng Đạo',	'2020-08-10',	2,		8000,	-1),
('son@gmail.com',	'son',		5,	3,	'1234567890',	N'596/37 Trần Hưng Đạo',	'2021-03-10',	1,		9100,	-1),
('nav@gmail.com',	'nav',		7,	2,	'1234567890',	N'596/37 Trần Hưng Đạo',	'2021-10-10',	1,		2300,	1),
('david@gmail.com',	'david',	3,	2,	'1234567890',	N'596/37 Trần Hưng Đạo',	'2021-10-10',	3,		2000,	-2)