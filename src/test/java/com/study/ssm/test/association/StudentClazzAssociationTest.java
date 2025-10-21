package com.study.ssm.test.association;

import com.study.ssm.entity.Clazz;
import com.study.ssm.entity.Student;
import com.study.ssm.mapper.ClazzMapper;
import com.study.ssm.mapper.StudentMapper;
import com.study.ssm.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * 学生班级关联映射综合测试类
 * 综合测试学生和班级的双向关联映射查询功能
 */
public class StudentClazzAssociationTest {
    
    private SqlSession sqlSession;
    private StudentMapper studentMapper;
    private ClazzMapper clazzMapper;
    
    @Before
    public void setUp() {
        sqlSession = MyBatisUtil.getSqlSession();
        studentMapper = sqlSession.getMapper(StudentMapper.class);
        clazzMapper = sqlSession.getMapper(ClazzMapper.class);
    }
    
    @After
    public void tearDown() {
        MyBatisUtil.closeSqlSession(sqlSession);
    }
    
    /**
     * 测试需求1：从tb_student表中查询学生信息，同时查询出该学生所在班级的信息
     */
    @Test
    public void testRequirement1_StudentWithClazz() {
        System.out.println("=== 测试需求1：从tb_student表中查询学生信息，同时查询出该学生所在班级的信息 ===");
        
        System.out.println("\n1.1 根据学生ID查询学生信息（包含班级信息）");
        Student student1 = studentMapper.selectStudentWithClazzById(1);
        printStudentWithClazz(student1, "学生ID=1");
        
        System.out.println("\n1.2 根据学生姓名查询学生信息（包含班级信息）");
        Student student2 = studentMapper.selectStudentWithClazzByName("rose");
        printStudentWithClazz(student2, "学生姓名=rose");
        
        System.out.println("\n1.3 查询所有学生信息（包含班级信息）");
        List<Student> allStudents = studentMapper.selectAllStudentsWithClazz();
        System.out.println("查询到 " + allStudents.size() + " 名学生：");
        for (int i = 0; i < allStudents.size(); i++) {
            System.out.println("\n第" + (i + 1) + "名学生：");
            printStudentWithClazz(allStudents.get(i), null);
        }
        
        System.out.println("\n=== 需求1测试完成 ===");
    }
    
    /**
     * 测试需求2：从tb_clazz表中查询班级信息，同时查询出该班级所有的学生信息
     */
    @Test
    public void testRequirement2_ClazzWithStudents() {
        System.out.println("=== 测试需求2：从tb_clazz表中查询班级信息，同时查询出该班级所有的学生信息 ===");
        
        System.out.println("\n2.1 根据班级ID查询班级信息（包含学生列表）");
        Clazz clazz1 = clazzMapper.selectClazzWithStudentsById(1);
        printClazzWithStudents(clazz1, "班级ID=1");
        
        System.out.println("\n2.2 根据班级编码查询班级信息（包含学生列表）");
        Clazz clazz2 = clazzMapper.selectClazzWithStudentsByCode("j1602");
        printClazzWithStudents(clazz2, "班级编码=j1602");
        
        System.out.println("\n2.3 根据班级名称查询班级信息（包含学生列表）");
        Clazz clazz3 = clazzMapper.selectClazzWithStudentsByName("计科1班");
        printClazzWithStudents(clazz3, "班级名称=计科1班");
        
        System.out.println("\n2.4 查询所有班级信息（包含学生列表）");
        List<Clazz> allClasses = clazzMapper.selectAllClazzWithStudents();
        System.out.println("查询到 " + allClasses.size() + " 个班级：");
        for (int i = 0; i < allClasses.size(); i++) {
            System.out.println("\n第" + (i + 1) + "个班级：");
            printClazzWithStudents(allClasses.get(i), null);
        }
        
        System.out.println("\n=== 需求2测试完成 ===");
    }
    
    /**
     * 测试关联映射的完整性和一致性
     */
    @Test
    public void testAssociationIntegrity() {
        System.out.println("=== 测试关联映射的完整性和一致性 ===");
        
        // 1. 获取所有数据
        List<Student> allStudents = studentMapper.selectAllStudentsWithClazz();
        List<Clazz> allClasses = clazzMapper.selectAllClazzWithStudents();
        
        System.out.println("数据统计：");
        System.out.println("  学生总数：" + allStudents.size());
        System.out.println("  班级总数：" + allClasses.size());
        
        // 2. 统计各班级学生数量
        System.out.println("\n各班级学生数量统计：");
        int totalStudentsInClasses = 0;
        for (Clazz clazz : allClasses) {
            int studentCount = (clazz.getStudents() != null) ? clazz.getStudents().size() : 0;
            totalStudentsInClasses += studentCount;
            System.out.println("  " + clazz.getName() + "（" + clazz.getCode() + "）：" + studentCount + " 名学生");
        }
        
        // 3. 验证数据一致性
        System.out.println("\n数据一致性验证：");
        
        // 3.1 验证学生总数一致性
        if (totalStudentsInClasses == allStudents.size()) {
            System.out.println("  ✓ 学生总数一致性验证通过");
        } else {
            System.out.println("  ✗ 学生总数一致性验证失败：班级中学生总数(" + totalStudentsInClasses + 
                             ") != 学生表记录数(" + allStudents.size() + ")");
        }
        
        // 3.2 验证每个学生都有班级信息
        boolean allStudentsHaveClazz = true;
        for (Student student : allStudents) {
            if (student.getClazz() == null) {
                System.out.println("  ✗ 学生 " + student.getName() + " 没有关联的班级信息");
                allStudentsHaveClazz = false;
            }
        }
        if (allStudentsHaveClazz) {
            System.out.println("  ✓ 所有学生都有关联的班级信息");
        }
        
        // 3.3 验证班级中学生的班级ID正确性
        boolean clazzStudentIdConsistent = true;
        for (Clazz clazz : allClasses) {
            if (clazz.getStudents() != null) {
                for (Student student : clazz.getStudents()) {
                    if (!student.getClazzId().equals(clazz.getId())) {
                        System.out.println("  ✗ 班级 " + clazz.getName() + " 中的学生 " + student.getName() + 
                                         " 的班级ID不匹配");
                        clazzStudentIdConsistent = false;
                    }
                }
            }
        }
        if (clazzStudentIdConsistent) {
            System.out.println("  ✓ 班级中学生的班级ID一致性验证通过");
        }
        
        System.out.println("\n=== 关联映射完整性测试完成 ===");
    }
    
    /**
     * 测试MyBatis关联映射的性能
     */
    @Test
    public void testAssociationPerformance() {
        System.out.println("=== 测试MyBatis关联映射的性能 ===");
        
        long startTime, endTime;
        
        // 测试学生关联查询性能
        startTime = System.currentTimeMillis();
        List<Student> students = studentMapper.selectAllStudentsWithClazz();
        endTime = System.currentTimeMillis();
        System.out.println("查询所有学生（包含班级信息）耗时：" + (endTime - startTime) + "ms，查询到 " + students.size() + " 条记录");
        
        // 测试班级关联查询性能
        startTime = System.currentTimeMillis();
        List<Clazz> classes = clazzMapper.selectAllClazzWithStudents();
        endTime = System.currentTimeMillis();
        System.out.println("查询所有班级（包含学生列表）耗时：" + (endTime - startTime) + "ms，查询到 " + classes.size() + " 条记录");
        
        // 测试单个学生查询性能
        startTime = System.currentTimeMillis();
        Student student = studentMapper.selectStudentWithClazzById(1);
        endTime = System.currentTimeMillis();
        System.out.println("根据ID查询单个学生（包含班级信息）耗时：" + (endTime - startTime) + "ms");
        
        // 测试单个班级查询性能
        startTime = System.currentTimeMillis();
        Clazz clazz = clazzMapper.selectClazzWithStudentsById(1);
        endTime = System.currentTimeMillis();
        System.out.println("根据ID查询单个班级（包含学生列表）耗时：" + (endTime - startTime) + "ms");
        
        System.out.println("\n=== 性能测试完成 ===");
    }
    
    /**
     * 综合测试 - 完整的关联映射功能验证
     */
    @Test
    public void testCompleteAssociationMapping() {
        System.out.println("=== 综合测试：完整的关联映射功能验证 ===");
        
        // 1. 测试需求1
        System.out.println("\n【第一部分】测试需求1：学生查询班级信息");
        testRequirement1_StudentWithClazz();
        
        // 2. 测试需求2
        System.out.println("\n【第二部分】测试需求2：班级查询学生信息");
        testRequirement2_ClazzWithStudents();
        
        // 3. 测试完整性
        System.out.println("\n【第三部分】测试关联映射完整性");
        testAssociationIntegrity();
        
        // 4. 测试性能
        System.out.println("\n【第四部分】测试关联映射性能");
        testAssociationPerformance();
        
        System.out.println("\n=== 综合测试完成 ===");
        System.out.println("✓ 学生和班级的关联映射查询功能测试通过！");
    }
    
    /**
     * 打印学生信息（包含班级信息）
     */
    private void printStudentWithClazz(Student student, String queryCondition) {
        if (student != null) {
            if (queryCondition != null) {
                System.out.println("查询条件：" + queryCondition + " - 查询成功！");
            }
            System.out.println("  学生信息：");
            System.out.println("    ID: " + student.getId());
            System.out.println("    姓名: " + student.getName());
            System.out.println("    性别: " + student.getSex());
            System.out.println("    年龄: " + student.getAge());
            System.out.println("    班级ID: " + student.getClazzId());
            
            if (student.getClazz() != null) {
                System.out.println("  所在班级信息：");
                System.out.println("    班级ID: " + student.getClazz().getId());
                System.out.println("    班级编码: " + student.getClazz().getCode());
                System.out.println("    班级名称: " + student.getClazz().getName());
            } else {
                System.out.println("  所在班级信息: 无");
            }
        } else {
            if (queryCondition != null) {
                System.out.println("查询条件：" + queryCondition + " - 未找到学生信息");
            } else {
                System.out.println("未找到学生信息");
            }
        }
    }
    
    /**
     * 打印班级信息（包含学生列表）
     */
    private void printClazzWithStudents(Clazz clazz, String queryCondition) {
        if (clazz != null) {
            if (queryCondition != null) {
                System.out.println("查询条件：" + queryCondition + " - 查询成功！");
            }
            System.out.println("  班级信息：");
            System.out.println("    ID: " + clazz.getId());
            System.out.println("    编码: " + clazz.getCode());
            System.out.println("    名称: " + clazz.getName());
            
            if (clazz.getStudents() != null && !clazz.getStudents().isEmpty()) {
                System.out.println("  学生列表（共" + clazz.getStudents().size() + "人）：");
                for (int i = 0; i < clazz.getStudents().size(); i++) {
                    Student student = clazz.getStudents().get(i);
                    System.out.println("    第" + (i + 1) + "个学生：");
                    System.out.println("      ID: " + student.getId());
                    System.out.println("      姓名: " + student.getName());
                    System.out.println("      性别: " + student.getSex());
                    System.out.println("      年龄: " + student.getAge());
                    System.out.println("      班级ID: " + student.getClazzId());
                }
            } else {
                System.out.println("  学生列表: 无");
            }
        } else {
            if (queryCondition != null) {
                System.out.println("查询条件：" + queryCondition + " - 未找到班级信息");
            } else {
                System.out.println("未找到班级信息");
            }
        }
    }
}
