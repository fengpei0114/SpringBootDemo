package com.example.demo.service;

import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.demo.fixture.TodoListFixture.mockTodoItem;
import static com.example.demo.fixture.TodoListFixture.mockTodoItemList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TodoListServiceTest {

    @Mock
    private TodoRepository todoRepository;

    private TodoListService service;

    @BeforeEach
    public void setUp() {
        service = new TodoListService(todoRepository);
    }

    @Nested
    class findAll{
        @Test
        public void shouldReturnAllTodoItemWhenFindAll() {
            var mockTodoList = mockTodoItemList();
            when(todoRepository.findAll()).thenReturn(mockTodoList);

            var actualTodoList = service.findAll();

            assertThat(actualTodoList).isEqualTo(mockTodoList);
        }
    }

    @Nested
    class save{
        @Test
        public void shouldReturnTodoItemWhenSuccessfulSave() {
            var mockTodoItem = mockTodoItem(1);

            when(todoRepository.save(any(Todo.class))).thenReturn(mockTodoItem);

            var actualTodoItem = service.save(mockTodoItem);

            verify(todoRepository, times(1)).save(mockTodoItem);
            assertThat(actualTodoItem).isEqualTo(mockTodoItem);
        }
    }

    @Nested
    class update{
        @Test
        public void shouldReturnTodoItemWhenSuccessfulUpdateGivenCurrentId() {
            var mockTodoItem = mockTodoItem(1);
            when(todoRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(mockTodoItem));

            var expectTodoItem = mockTodoItem(1);
            expectTodoItem.setStatus(true);
            when(todoRepository.save(any(Todo.class))).thenReturn(expectTodoItem);

            var actualTodoItem = service.update(expectTodoItem);

            verify(todoRepository, times(1)).findById(mockTodoItem.getId());
            assertThat(actualTodoItem).isEqualTo(expectTodoItem);
        }

        @Test
        public void shouldThrowErrorGivenWrongTodoItem() {
            var expectTodoItem = mockTodoItem(1);
            expectTodoItem.setStatus(true);
            when(todoRepository.findById(any(Long.class))).thenReturn(null);

           assertThatThrownBy(() -> service.update(expectTodoItem)).isInstanceOf(RuntimeException.class);
        }
    }

    @Nested
    class delete{
        @Test
        void shouldExecuteSuccessWhenDeleteTodoItem() {
            var mockTodoItem = mockTodoItem(1);

            when(todoRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(mockTodoItem));
            doNothing().when(todoRepository).deleteById(any(Long.class));

            service.delete(mockTodoItem.getId());

            verify(todoRepository, times(1)).findById(mockTodoItem.getId());
            verify(todoRepository, times(1)).deleteById(mockTodoItem.getId());
        }

        @Test
        public void shouldThrowErrorGivenWrongId() {
            var mockTodoItem = mockTodoItem(1);
            when(todoRepository.findById(any(Long.class))).thenThrow(new RuntimeException("throw error"));

            assertThatThrownBy(() -> service.delete(mockTodoItem.getId())).isInstanceOf(RuntimeException.class);
        }
    }
}
