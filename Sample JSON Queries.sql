#Tìm các sản phẩm của Razer ~ Tìm theo key/value trong JSON
SELECT *
FROM product
WHERE JSON_CONTAINS(attributes, '{"data": "Razer"}', "$.attributes");

# Query after modifying sample data
SELECT *
FROM product
WHERE attributes->'$."Nhà sản xuất"' LIKE '%Razer%';

#Tìm các mẫu CHUỘT của Razer
SELECT *
FROM product
WHERE JSON_CONTAINS(attributes, '{"data": "Razer"}', "$.attributes") AND category_id = 1;

SELECT *
FROM product
WHERE attributes->'$."Nhà sản xuất"' LIKE '%Razer%' AND category_id = 1;

