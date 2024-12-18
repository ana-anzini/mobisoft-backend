package com.mobisoft.mobisoftapi.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.mobisoft.mobisoftapi.configs.exceptions.ProjectNotFoundException;
import com.mobisoft.mobisoftapi.dtos.project.ProjectDTO;
import com.mobisoft.mobisoftapi.enums.project.StatusType;
import com.mobisoft.mobisoftapi.models.Costumer;
import com.mobisoft.mobisoftapi.models.Project;
import com.mobisoft.mobisoftapi.models.User;
import com.mobisoft.mobisoftapi.models.UserGroup;
import com.mobisoft.mobisoftapi.repositories.ProjectRepository;
import com.mobisoft.mobisoftapi.services.ProjectService;
import com.mobisoft.mobisoftapi.services.CostumerService;
import com.mobisoft.mobisoftapi.services.EmployeesService;
import com.mobisoft.mobisoftapi.services.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

class ProjectServiceTest {

    @InjectMocks
    private ProjectService projectService;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private CostumerService costumerService;

    @Mock
    private EmployeesService employeesService;

    @Mock
    private UserService userService;

    @Mock
    private Project project;

    @Mock
    private ProjectDTO projectDTO;

    @Mock
    private UserGroup userGroup;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        User mockUser = mock(User.class);
        when(userService.getLoggedUser()).thenReturn(mockUser);

        UserGroup mockUserGroup = mock(UserGroup.class);
        when(mockUser.getGroup()).thenReturn(mockUserGroup);
    }

    @Test
    void testGetProjectById() {
        Long projectId = 1L;
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));

        Project result = projectService.getProjectById(projectId);

        assertNotNull(result);
        verify(projectRepository, times(1)).findById(projectId);
    }

    @Test
    void testGetProjectByIdNotFound() {
        Long projectId = 1L;
        when(projectRepository.findById(projectId)).thenReturn(Optional.empty());

        assertThrows(ProjectNotFoundException.class, () -> projectService.getProjectById(projectId));
    }

    @Test
    void testGetAllProjects() {
        List<Project> projects = new ArrayList<>();
        projects.add(project);
        when(projectRepository.findByUserGroupId(anyLong())).thenReturn(projects);

        List<Project> result = projectService.getAllProjects();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(projectRepository, times(1)).findByUserGroupId(anyLong());
    }

    @Test
    void testUpdateProjectNotFound() {
        Long projectId = 1L;
        when(projectRepository.findById(projectId)).thenReturn(Optional.empty());

        assertThrows(ProjectNotFoundException.class, () -> projectService.updateProject(projectId, projectDTO));
    }

    @Test
    void testDeleteProject() {
        Long projectId = 1L;
        when(projectRepository.existsById(projectId)).thenReturn(true);

        projectService.deleteProject(projectId);

        verify(projectRepository, times(1)).deleteById(projectId);
    }

    @Test
    void testDeleteProjectNotFound() {
        Long projectId = 1L;
        when(projectRepository.existsById(projectId)).thenReturn(false);

        assertThrows(ProjectNotFoundException.class, () -> projectService.deleteProject(projectId));
    }

    @Test
    void testDeleteProjects() {
        List<Long> ids = Arrays.asList(1L, 2L);
        List<Project> projects = Arrays.asList(project);
        when(projectRepository.findAllById(ids)).thenReturn(projects);

        projectService.deleteProjects(ids);

        verify(projectRepository, times(1)).deleteAll(projects);
    }
}