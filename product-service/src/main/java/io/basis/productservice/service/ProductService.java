package io.basis.productservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.basis.productservice.custom.exception.ProductNotFoundException;
import io.basis.productservice.model.Product;
import io.basis.productservice.repository.ProductRepository;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.*;

import static io.basis.productservice.custom.constant.ImportConstant.*;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findByAttribute(String attribute, String value) {
        return productRepository.findByJsonAttribute(attribute, value);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product create(Product product) {
        productRepository.save(product);
        productRepository.flush();
        return product;
    }

    public List<Product> createSampleProduct(int websiteId, int categoryId){
        List<Product>products= new ArrayList<>();


        for (int i = 0; i < 5; i++) {
            Product product = new Product();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            ObjectMapper objectMapper = new ObjectMapper();

            product.setName("Razer Lancehead");
            product.setPrice(3590000);
            product.setDiscount(0.15);
            product.setCreatedDate(timestamp);
            product.setModifiedDate(null);
            product.setStatus(1);
            product.setEnabled(1);
            product.setDescription("Chuột không dây, đẹp nhưng mắc vãi đái.");
            Map<String, String> attributes = new HashMap<>();
            attributes.put("Đèn LED", "RGB");
            attributes.put("Bảo hành", "24 tháng");
            attributes.put("Tình trạng", "Mới 100% - Full box");
            attributes.put("Nhà sản xuất", "Razer");
            try {
                product.setAttributes(objectMapper.writeValueAsString(attributes));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            List<String> images = Arrays.asList("http://hstatic.net/716/1000026716/1/2016/8-30/ra2.png",
                    "http://hstatic.net/716/1000026716/1/2016/4-11/razer-blackwidow-x-chroma-2.png",
                    "http://hstatic.net/716/1000026716/1/2016/4-11/razer-blackwidow-x-chroma-4.png");
            try {
                product.setImages(objectMapper.writeValueAsString(images));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            product.setCategoryId(categoryId);
            product.setWebsiteId(websiteId);
            products.add(product);
        }
        productRepository.saveAll(products);
        return products;
    }

    public List<Product> findByCategoryId(int categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public Optional<Product> findById(int productId) {
        return productRepository.findById(productId);
    }

    public Product update(int productId, Product modifiedProduct)
            throws ProductNotFoundException {
        return productRepository.findById(productId).map(product -> {
            product.setName(modifiedProduct.getName());

            product.setPrice(modifiedProduct.getPrice());
            product.setDiscount(modifiedProduct.getDiscount());

            product.setStatus(modifiedProduct.getStatus());

            product.setModifiedDate(new Timestamp(System.currentTimeMillis()));

            productRepository.save(product);
            productRepository.flush();

            return product;
        }).orElseThrow(ProductNotFoundException::new);
    }

    public Product updateAttribute(int productId, String attributes) throws ProductNotFoundException {
        return productRepository.findById(productId).map(product -> {
            product.setAttributes(attributes);

            product.setModifiedDate(new Timestamp(System.currentTimeMillis()));

            productRepository.save(product);
            productRepository.flush();

            return product;
        }).orElseThrow(ProductNotFoundException::new);
    }

    public Product updateImages(int productId, String images) throws ProductNotFoundException {
        return productRepository.findById(productId).map(product -> {
            product.setImages(images);

            product.setModifiedDate(new Timestamp(System.currentTimeMillis()));

            productRepository.save(product);
            productRepository.flush();

            return product;
        }).orElseThrow(ProductNotFoundException::new);
    }

    private File handleMultipartFile(MultipartFile multipartFile) {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        if (!multipartFile.isEmpty()) {
            try {
                byte[] bytes = multipartFile.getBytes();
                FileUtils.writeByteArrayToFile(file, bytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public void importProducts(MultipartFile excel) {
        File file = handleMultipartFile(excel);
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row headers = rowIterator.next();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                int cellIndex = 0;
                Product product = new Product();
                Map<String, String> attributes = new HashMap<>();
                Iterator<Cell> headerIterator = headers.cellIterator();
                while (headerIterator.hasNext()) {
                    Cell header = headerIterator.next();
                    Cell cell = row.getCell(cellIndex);
                    switch (header.getStringCellValue()) {
                        case HEADER_NAME: {
                            if (null == cell || Cell.CELL_TYPE_BLANK == cell.getCellType()) {
                                product.setName(DEFAULT_NAME);
                            } else {
                                product.setName(cell.getStringCellValue());
                            }
                            break;
                        }
                        case HEADER_PRICE: {
                            if (null == cell || Cell.CELL_TYPE_BLANK == cell.getCellType() || Cell.CELL_TYPE_NUMERIC != cell.getCellType()) {
                                product.setPrice(DEFAULT_PRICE);
                            } else {
                                product.setPrice(cell.getNumericCellValue());
                            }
                            break;
                        }
                        case HEADER_DISCOUNT: {
                            if (null == cell || Cell.CELL_TYPE_BLANK == cell.getCellType() || Cell.CELL_TYPE_NUMERIC != cell.getCellType()) {
                                product.setDiscount(DEFAULT_DISCOUNT);
                            } else {
                                product.setDiscount(cell.getNumericCellValue());
                            }
                            break;
                        }
                        case HEADER_STATUS: {
                            if (null == cell || Cell.CELL_TYPE_BLANK == cell.getCellType() || Cell.CELL_TYPE_NUMERIC != cell.getCellType()) {
                                product.setStatus(DEFAULT_STATUS);
                            } else {
                                product.setStatus((int) cell.getNumericCellValue());
                            }
                            break;
                        }
                        case HEADER_DESCRIPTION: {
                            if (null == cell || Cell.CELL_TYPE_BLANK == cell.getCellType()) {
                                product.setDescription(DEFAULT_DESCRIPTION);
                            } else {
                                product.setDescription(cell.getStringCellValue());
                            }
                            break;
                        }
                        case HEADER_CATEGORY: {
                            if (null == cell || Cell.CELL_TYPE_BLANK == cell.getCellType() || Cell.CELL_TYPE_NUMERIC != cell.getCellType()) {
                                product.setCategoryId(DEFAULT_CATEGORY_ID);
                            } else {
                                product.setCategoryId((int) cell.getNumericCellValue());
                            }
                            break;
                        }
                        default: {
                            if (null != cell && Cell.CELL_TYPE_BLANK != cell.getCellType()) {
                                attributes.put(header.getStringCellValue(), cell.getStringCellValue());
                            }
                            break;
                        }
                    }
                    cellIndex++;
                }
                System.out.println("New Product:");
                System.out.println("    Product Name: " + product.getName());
                System.out.println("    Price: " + product.getPrice());
                System.out.println("    Discount: " + product.getDiscount());
                System.out.println("    Status: " + product.getStatus());
                System.out.println("    Description: " + product.getDescription());
                System.out.println("    Category ID: " + product.getCategoryId());
                System.out.println("    Attributes: " + attributes);
                System.out.println("============");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
