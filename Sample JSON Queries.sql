#Tìm các sản phẩm của Razer ~ Tìm theo key/value trong JSON
SELECT * FROM product WHERE JSON_CONTAINS(attributes, '{"data": "Razer"}', "$.attributes");

#Tìm các mẫu CHUỘT của Razer
SELECT * FROM product WHERE JSON_CONTAINS(attributes, '{"data": "Razer"}', "$.attributes") AND category_id = 1;

