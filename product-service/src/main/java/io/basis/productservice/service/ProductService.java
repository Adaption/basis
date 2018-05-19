package io.basis.productservice.service;

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
