package com.crud.tasks.trello.client;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TrelloClientTest {

    @InjectMocks
    private TrelloClient trelloClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TrelloConfig trelloConfig;

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Before
    public void init() {
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");
    }

/*    @Test
    public void shouldFetchTrelloBoards() throws URISyntaxException {
        //Given
        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto("test_board", "test_id", new ArrayList<>());

        URI uri = new URI("http://test.com/members/tomaszpieniezny/boards?key=test&token=test&fields=name,id&lists=all");

        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(trelloBoards);

        //When
        List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();

        //Then
        assertEquals(1, fetchedTrelloBoards.size());
        assertEquals("test_id", fetchedTrelloBoards.get(0).getId());
        assertEquals("test_board", fetchedTrelloBoards.get(0).getName());
        assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());
    }*/
    @Test
    public void shouldCreateCard() throws URISyntaxException {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Test task",
                "Test Description",
                "top",
                "test_id"
        );
        URI uri = new URI("http://test.com/cards?key=test&token=test&name=Test%20task&desc=Test%20Description&pos=top&idList=test_id");

        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto(
                "1",
                "Test task",
                "http://test.com"
        );

        when(restTemplate.postForObject(uri, null, CreatedTrelloCardDto.class)).thenReturn(createdTrelloCardDto);

        // When
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);

        //Then
        assertEquals("1", newCard.getId());
        assertEquals("Test task", newCard.getName());
        assertEquals("http://test.com", newCard.getShortUrl());

    }

    @Test
    public void shouldReturnEmptyList() throws URISyntaxException {
        //Given
        TrelloBoardDto[] emptyBoard = new TrelloBoardDto[0];

        URI uri = new URI("http://test.com/members/tomaszpieniezny/boards?key=test&token=test&fields=name,id&lists=all");

        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(emptyBoard);

        //When
        List<TrelloBoardDto> fetchedEmptyTrelloBoard = trelloClient.getTrelloBoards();

        //Then
        assertEquals(0, fetchedEmptyTrelloBoard.size());
    }

    @Test
    public void canMapToBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(new TrelloBoardDto("test_id", "test_board", new ArrayList<>()));

        //When
        List<TrelloBoard> theList = trelloMapper.mapToBoards(trelloBoardDtoList);

        //Then
        assertEquals(1, theList.size());
    }

    @Test
    public void canMapToBoardsDto() {
        //Given
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(new TrelloBoard("test_id", "test_board", new ArrayList<>()));

        //When
        List<TrelloBoardDto> theList = trelloMapper.mapToBoardsDto(trelloBoardList);

        //Then
        assertEquals(1, theList.size());
    }

    @Test
    public void canMapToList() {
        //Given
        List<TrelloListDto> trelloList = new ArrayList<>();
        trelloList.add(new TrelloListDto("test_id", "test_list", true));

        //When
        List<TrelloList> theList = trelloMapper.mapToLists(trelloList);

        //Then
        assertEquals(1, theList.size());
    }

    @Test
    public void canMapToListDto() {
        //Given
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(new TrelloList("test_id", "test_list", true));

        //When
        List<TrelloListDto> theList = trelloMapper.mapToListsDto(trelloList);

        //Then
        assertEquals(1, theList.size());
    }

    @Test
    public void canMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test_name", "test_description",
                "test_pos", "test_listId");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("test_name", trelloCardDto.getName());
        assertEquals("test_description", trelloCardDto.getDescription());
        assertEquals("test_pos", trelloCardDto.getPos());
        assertEquals("test_listId", trelloCardDto.getListId());
    }

    @Test
    public void canMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test_name", "test_description",
                "test_pos", "test_listId");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("test_name", trelloCard.getName());
        assertEquals("test_description", trelloCard.getDescriotion());
        assertEquals("test_pos", trelloCard.getPos());
        assertEquals("test_listId", trelloCard.getListId());
    }


}