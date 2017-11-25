package com.ymd.ddshop.lucene.manager;

import com.ymd.ddshop.lucene.Impl.BookDaoImpl;
import com.ymd.ddshop.lucene.dao.IbookDao;
import com.ymd.ddshop.lucene.po.Book;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IndexManager {
    @Test
    public void createIndex() throws IOException {
        //采集数据
        IbookDao bookDao = new BookDaoImpl();
        List<Book> bookList = bookDao.listBooks();
        //将采集数据保存到文档域中
        List<Document> documentList = new ArrayList<Document>();

        for (Book book : bookList) {
            Document document = new Document();
            //图书ID：不分词、索引、存储
            Field id = new StringField("id", book.getId().toString(), Field.Store.YES);
            //图书名称：分词、索引、存储
            Field name = new TextField("name", book.getName(), Field.Store.YES);
            //图书价格：不分词、索引、存储
            Field price = new FloatField("price", book.getPrice(), Field.Store.YES);
            //图片地址：不分词、不索引、存储
            Field pic = new StoredField("pic", book.getPic());
            //图片描述：分词、索引、不存储
            Field description = new TextField("description", book.getDescription(), Field.Store.NO);

            //将field添加到document中
            document.add(id);
            document.add(name);
            document.add(price);
            document.add(pic);
            document.add(description);
            //添加到文档域集合中
            documentList.add(document);
        }

        //创建分词器，使用Lucene默认分词器
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig cfg = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
        //指定索引库的地址
        File indexFile = new File("G:\\bookindex");
        Directory directory = FSDirectory.open(indexFile);
        IndexWriter writer = new IndexWriter(directory, cfg);
        //创建索引，将每个文档域对象存放到索引库中
        for (Document doc : documentList) {
            writer.addDocument(doc);
        }
        //关闭连接
        writer.close();
    }

    public void updateIndex()throws  Exception{
        //创建分词器，使用Lucene默认分词器
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig cfg = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
        //指定索引库的地址
        File indexFile = new File("G:\\bookindex");
        Directory directory = FSDirectory.open(indexFile);
        IndexWriter writer = new IndexWriter(directory, cfg);

        Term term = new Term("name","solr");
        Document document = new Document();
        document.add(new TextField("name","javasript",Field.Store.YES));

        writer.updateDocument(term,document);
        writer.close();
    }
}
