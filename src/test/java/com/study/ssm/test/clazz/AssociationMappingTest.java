package com.study.ssm.test.clazz;

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
 * 学生班级关联映射测试类
 * 测试MyBatis关联映射功能
 */
public class AssociationMappingTest {
    
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
    public void testStudentWithClazz() {
        System.out.println("=== 测试需求1：从tb_student表中查询学生信息，同时查询出该学生所在班级的信息 ===");
        
        // 1. 根据学生ID查询学生信息（包含班级信息）
        System.out.println("\n1. 根据学生ID查询学生信息（包含班级信息）");
        Student student1 = studentMapper.selectStudentWithClazzById(1);
        printStudentWithClazz(student1, "学生ID=1");
        
        // 2. 根据学生姓名查询学生信息（包含班级信息）
        System.out.println("\n2. 根据学生姓名查询学生信息（包含班级信息）");
        Student student2 = studentMapper.selectStudentWithClazzByName("rose");
        printStudentWithClazz(student2, "学生姓名=rose");
        
        // 3. 查询所有学生信息（包含班级信息）
        System.out.println("\n3. 查询所有学生信息（包含班级信息）");
        List<Student> allStudents = studentMapper.selectAllStudentsWithClazz();
        System.out.println("查询到 " + allStudents.size() + " 名学生：");
        for (int i = 0; i < allStudents.size(); i++) {
            System.out.println("\n第" + (i + 1) + "名学生：");
            printStudentWithClazz(allStudents.get(i), null);
        }
        
        // 4. 根据班级ID查询该班级的所有学生（包含班级信息）
        System.out.println("\n4. 根据班级ID查询该班级的所有学生（包含班级信息）");
        List<Student> studentsInClazz = studentMapper.selectStudentsByClazzId(1);
        System.out.println("班级ID=1 中有 " + studentsInClazz.size() + " 名学生：");
        for (int i = 0; i < studentsInClazz.size(); i++) {
            System.out.println("\n第" + (i + 1) + "名学生：");
            printStudentWithClazz(studentsInClazz.get(i), null);
        }
        
        System.out.println("\n=== 需求1测试完成 ===");
    }
    
    /**
     * 测试需求2：从tb_clazz表中查询班级信息，同时查询出该班级所有的学生信息
     */
    @Test
    public void testClazzWithStudents() {
        System.out.println("=== 测试需求2：从tb_clazz表中查询班级信息，同时查询出该班级所有的学生信息 ===");
        
        // 1. 根据班级ID查询班级信息（包含学生列表）
        System.out.println("\n1. 根据班级ID查询班级信息（包含学生列表）");
        Clazz clazz1 = clazzMapper.selectClazzWithStudentsById(1);
        printClazzWithStudents(clazz1, "班级ID=1");
        
        // 2. 根据班级编码查询班级信息（包含学生列表）
        System.out.println("\n2. 根据班级编码查询班级信息（包含学生列表）");
        Clazz clazz2 = clazzMapper.selectClazzWithStudentsByCode("j1602");
        printClazzWithStudents(clazz2, "班级编码=j1602");
        
        // 3. 根据班级名称查询班级信息（包含学生列表）
        System.out.println("\n3. 根据班级名称查询班级信息（包含学生列表）");
        Clazz clazz3 = clazzMapper.selectClazzWithStudentsByName("计科1班");
        printClazzWithStudents(clazz3, "班级名称=计科1班");
        
        // 4. 查询所有班级信息（包含学生列表）
        System.out.println("\n4. 查询所有班级信息（包含学生列表）");
        List<Clazz> allClasses = clazzMapper.selectAllClazzWithStudents();
        System.out.println("查询到 " + allClasses.size() + " 个班级：");
        for (int i = 0; i < allClasses.size(); i++) {
            System.out.println("\n第" + (i + 1) + "个班级：");
            printClazzWithStudents(allClasses.get(i), null);
        }
        
        System.out.println("\n=== 需求2测试完成 ===");
    }
    
    /**
     * 综合测试：验证关联映射的完整性
     */
    @Test
    public void testAssociationMappingIntegrity() {
        System.out.println("=== 综合测试：验证关联映射的完整性 ===");
        
        // 测试需求1
        System.out.println("\n【第一部分】测试需求1：学生查询班级信息");
        testStudentWithClazz();
        
        // 测试需求2
        System.out.println("\n【第二部分】测试需求2：班级查询学生信息");
        testClazzWithStudents();
        
        // 数据一致性验证
        System.out.println("\n【第三部分】数据一致性验证");
        List<Student> allStudents = studentMapper.selectAllStudentsWithClazz();
        List<Clazz> allClasses = clazzMapper.selectAllClazzWithStudents();
        
        System.out.println("数据统计：");
        System.out.println("  学生总数：" + allStudents.size());
        System.out.println("  班级总数：" + allClasses.size());
        
        // 统计各班级学生数量
        System.out.println("\n各班级学生数量统计：");
        int totalStudentsInClasses = 0;
        for (Clazz clazz : allClasses) {
            int studentCount = (clazz.getStudents() != null) ? clazz.getStudents().size() : 0;
            totalStudentsInClasses += studentCount;
            System.out.println("  " + clazz.getName() + "（" + clazz.getCode() + "）：" + studentCount + " 名学生");
        }
        
        // 验证数据一致性
        System.out.println("\n数据一致性验证：");
        if (totalStudentsInClasses == allStudents.size()) {
            System.out.println("  ✓ 学生总数一致性验证通过");
        } else {
            System.out.println("  ✗ 学生总数一致性验证失败");
        }
        
        // 验证每个学生都有班级信息
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
